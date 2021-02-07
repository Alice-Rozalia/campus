<template>
  <div class="login-container">
    <!-- 星星 -->
    <div class="stars">
      <div class="star" :ref="star" v-for="(item,index) in starCount" :key="index"></div>
    </div>

    <div class="login-box">
      <div class="login-title">登录</div>
      <el-form>
        <el-form-item>
          <el-input prefix-icon="el-icon-user" placeholder="用户名" />
        </el-form-item>
        <el-form-item>
          <el-input prefix-icon="el-icon-lock" placeholder="密码" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width: 100%">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs, ref } from 'vue'

export default defineComponent({
  name: 'Login',
  setup() {
    const state = reactive({
      // 星星数量
      starCount: 800,
      // 间距
      distance: 800
    })

    let stars: any[] = []
    const star = (el: HTMLElement) => {
      stars.push(el)
    }

    onMounted(() => {
      stars.forEach((item: HTMLElement) => {
        let spend = 0.2 + Math.random() * 1
        let thisDistance = state.distance + Math.random() * 300
        item.style.transformOrigin = `0 0 ${thisDistance}px`
        item.style.transform = `translate3d(0,0,-${thisDistance}px) rotateY(${Math.random() * 360}deg) rotateX(${Math.random() * -50}deg) scale(${spend},${spend})`
      })
    })

    return {
      ...toRefs(state),
      star
    }
  }
})
</script>

<style lang="less" scoped>
  @import "../styles/login.less";
</style>
