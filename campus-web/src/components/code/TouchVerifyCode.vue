<template>
  <div class="verify-code-wrap" :style="{ width: containerSize.width + 'px' }">
    <div class="verify-canvas-container">
      <canvas :width="containerSize.width" :height="containerSize.height" ref="container"/>
      <canvas :width="containerSize.width" :height="containerSize.height" ref="slide" class="verify-slide-block"/>
      <span :class="['verify-refresh', {'verify-refresh__progress': !verifyImageLoaded}]" @click="onRefresh">
        <i class="el-icon-refresh-left"></i>
      </span>
      <transition name="slide">
        <div class="verify-time"
             v-show="verifySuccess">验证成功~本次验证共计{{ verifyUse }}秒
        </div>
      </transition>
      <transition name="fade">
        <div v-if="!verifyImageLoaded"
             class="verify-loading-image">图片加载中...请稍等
        </div>
      </transition>
    </div>
    <div
      :class="['touch-verify-bar',
      {'touch-verify-bar__progress': verifying, 'touch-verify-bar__success': verifySuccess, 'touch-verify-bar__failed': verifyFailed}]">
      <div class="touch-verify-slide-bar" :style="{ width: verifyProgressWidth }">
        <div class="touch-verify-slide-block" :style="{ left: blockLeftValue }" @mousedown="onBlockTouch">
          <i v-if="verifySuccess" class="el-icon-check"></i>
          <i v-else-if="verifyFailed" class="el-icon-close"></i>
          <i v-else class="el-icon-arrow-right"></i>
        </div>
      </div>
      <p v-show="showVerifyBarText" v-html="barText"/>
    </div>
  </div>
</template>

<script lang="ts">
import {
  computed,
  defineComponent,
  onMounted,
  onUnmounted,
  PropType,
  reactive,
  ref,
} from "vue"
import useCanvasApi from "@/hooks/code/useCanvasApi"
import useImage from "@/hooks/code/useImage"
import { calculate, onCreateRandomRange, square } from "@/helper/index"
import useMouseEvent from "@/hooks/code/useMouseEvent"

interface IContainer {
  width: number;
  height: number;
}

const BLOCK_SIZE = 48
const TouchVerifyCode = defineComponent({
  name: "TouchVerifyCode",
  props: {
    containerSize: {
      type: Object as PropType<IContainer>,
      default: {
        width: 400,
        height: 250,
      },
    },
    slideSize: {
      type: Number,
      default: 56,
    },
    slideRadius: {
      type: Number,
      default: 10,
    },
    accuracy: {
      type: Number,
      default: 5,
    },
    barText: {
      type: String,
      default: "&lt;&lt; 按住滑块，向右拖动 &gt;&gt;",
    },
    resource: {
      type: Object as PropType<string[]>,
      default: [],
    },
  },
  emits: ["success", "failed", "after-reset", "refresh"],
  setup(props, { emit }) {
    const {
      resource,
      slideSize: size,
      slideRadius: radius,
      containerSize: { width, height },
    } = props
    const { onDraw } = useCanvasApi(size, radius)
    const { onImageCreate, onCreateRandomImageByNets } = useImage(
      resource,
      width,
      height
    )
    // 容器
    let container = ref<HTMLCanvasElement | null>(null)
    let slide = ref<HTMLCanvasElement | null>(null)
    let con_ctx = ref<CanvasRenderingContext2D | null>(null)
    let slide_ctx = ref<CanvasRenderingContext2D | null>(null)
    // 滑块验证相关
    let verifyImage = ref<HTMLImageElement | null>(null)
    let verifyImageLoaded = ref(false)
    let verifying = ref(false)
    let verifySuccess = ref(false)
    let verifyFailed = ref(false)
    let verifyProgressWidth = ref("0px")
    let verifyStart = ref(false)
    let verifyCoordinate = reactive({ x: 0, y: 0 })
    let verifyTimestamp = ref<number>(0)
    let verifyUse = computed(() => verifyTimestamp.value / 1000)
    let verifyTrailArr = ref<number[]>([])
    let showVerifyBarText = computed(
      () => !verifyStart.value && !verifySuccess.value && !verifyFailed.value
    )
    let blockLeftValue = ref("0px")
    let slideSize = size + radius * 2 + 3
    const slideOptions = reactive({
      x: 0,
      y: 0,
    })
    const onDomInit = () => {
      con_ctx.value = <CanvasRenderingContext2D>(
        container.value?.getContext("2d")
      )
      slide_ctx.value = <CanvasRenderingContext2D>slide.value?.getContext("2d")
    }
    const onImageInit = () => {
      const image = onImageCreate(() => {
        verifyImageLoaded.value = true
        onSlideDrew()
        con_ctx.value?.drawImage(image, 0, 0, width, height)
        slide_ctx.value?.drawImage(image, 0, 0, width, height)
        const _y = slideOptions.y - radius * 2 - 1
        const ImageData = slide_ctx.value!.getImageData(
          slideOptions.x,
          _y,
          slideSize,
          slideSize
        )
        slide.value!.width = slideSize
        slide_ctx.value?.putImageData(ImageData, 0, _y)
      })
      verifyImage.value = image
    }
    // 绘制卡片块
    const onSlideDrew = () => {
      const size = slideSize + 10
      slideOptions.x = onCreateRandomRange(size, width - size)
      slideOptions.y = onCreateRandomRange(radius * 2 + 10, height - size)
      onDraw(con_ctx.value!, slideOptions.x, slideOptions.y, "fill")
      onDraw(slide_ctx.value!, slideOptions.x, slideOptions.y, "clip")
    }
    // 设置状态，起始坐标
    const onBlockTouch = (event: MouseEvent) => {
      if (verifySuccess.value) return false
      if (!verifyImageLoaded.value) return false
      verifyCoordinate.x = event.clientX
      verifyCoordinate.y = event.clientY
      verifyTimestamp.value = +new Date()
      verifyStart.value = true
    }
    const onBlockMove = (event: MouseEvent) => {
      if (!verifyStart.value) return false
      const verifyMoveX = event.clientX - verifyCoordinate.x
      const verifyMoveY = event.clientY - verifyCoordinate.y
      if (verifyMoveX < 0 || verifyMoveX + BLOCK_SIZE >= width) return false
      const moveStr = verifyMoveX + "px"
      const leftValue =
        ((width - 50 - radius * 2) / (width - 50)) * verifyMoveX
      verifying.value = true
      slide.value!.style.left = leftValue + "px"
      blockLeftValue.value = verifyMoveX - 2 + "px"
      verifyProgressWidth.value = moveStr
      verifyTrailArr.value.push(verifyMoveX)
    }
    // 当离开滑块时进行正确性的判断
    const onBlockTouchLeave = (event: MouseEvent) => {
      if (!verifyStart.value) return false
      verifyStart.value = false
      if (event.clientX === verifyCoordinate.x) return false
      verifying.value = false
      verifyTimestamp.value = +new Date() - verifyTimestamp.value!
      const { correct, userOperator } = onVerify()
      if (correct) {
        // 跳过人机验证
        if (props.accuracy === -1) return onVerifySuccess()
        // 如果是人机 则需重新验证
        if (userOperator) return onVerifySuccess()
        else return onVerifyFailed()
      } else onVerifyFailed()
    }
    // 边界处理 防止鼠标拖动过程中移出滑块之外
    const onBlockOutside = (event: MouseEvent) => {
      if (!verifyStart.value) return false
      onVerifyFailed()
    }
    // 成功
    const onVerifySuccess = () => {
      verifySuccess.value = true
      emit("success", verifyUse.value)
    }
    // 失败
    const onVerifyFailed = () => {
      verifyFailed.value = true
      setTimeout(onReset, 1000)
      emit("failed")
    }
    // 重置所有滑块相关的状态
    const onReset = () => {
      verifyStart.value = false
      verifyImageLoaded.value = false
      verifying.value = false
      verifySuccess.value = false
      verifyFailed.value = false
      verifyProgressWidth.value = "0px"
      slide.value!.style.left = "0px"
      blockLeftValue.value = "0px"
      con_ctx.value?.clearRect(0, 0, width, height)
      slide_ctx.value?.clearRect(0, 0, width, height)
      slide.value!.width = width
      verifyImage.value!.src = onCreateRandomImageByNets()
      emit("after-reset")
    }
    // 验证逻辑
    const onVerify = () => {
      const verifyArr = verifyTrailArr.value.slice(0)
      // 计算平均值
      const avg = verifyArr.reduce(calculate) / verifyArr.length
      // 计算偏差值
      const deviations = verifyArr.map((x) => x - avg)
      // 标准偏差值
      const standard_deviations = Math.sqrt(
        deviations.map(square).reduce(calculate) / verifyArr.length
      )
      const blockMoveX = parseInt(slide.value!.style.left)
      const accuracy =
        props.accuracy <= 1 ? 1 : props.accuracy >= 10 ? 10 : props.accuracy
      return {
        correct: Math.abs(blockMoveX - slideOptions.x) <= accuracy,
        userOperator: avg !== standard_deviations,
      }
    }
    const onRefresh = () => {
      onReset()
      emit("refresh")
    }
    const { initEvent } = useMouseEvent(onBlockMove, onBlockTouchLeave)
    const init = () => {
      onDomInit()
      onImageInit()
      initEvent()
    }
    onMounted(init)
    return {
      container,
      slide,
      verifying,
      verifyStart,
      verifySuccess,
      verifyFailed,
      verifyProgressWidth,
      verifyImageLoaded,
      verifyUse,
      showVerifyBarText,
      blockLeftValue,
      onBlockTouch,
      onBlockMove,
      onBlockTouchLeave,
      onBlockOutside,
      onRefresh,
    }
  },
})
export default TouchVerifyCode
</script>

<style lang="less" scoped>
  @import "../../styles/verifyCode.less";
</style>
