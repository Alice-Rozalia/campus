<template>
  <div class="main-container">
    <Header/>

    <div class="main-warp">
      <Banner/>

      <div class="goods">
        <water-fall width="290px" gap="10px" :delay="true" :data="images">
          <template #default="item">
            <div class="card">
              <img :src="item.src"/>
              <div class="card-info">
                <p class="goods-name">{{item.name}}</p>
                <div class="goods-publish">
                  <span class="el-icon-time"> 2021-1-1 15:02:35</span>
                  <span class="el-icon-view"> 35</span>
                </div>
                <div class="introduce">
                  目前可以通过 unpkg.com/element-ui 获取到最新版本的资源，在页面上引入 js 和 css 文件即可开始使用。
                </div>
                <div class="goods-price-addr">
                  <span class="goods-price">￥200</span>
                  <span class="goods-addr">成都某大专</span>
                </div>
              </div>
            </div>
          </template>
        </water-fall>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs } from 'vue'
import Header from '@/components/Header.vue'
import Banner from '@/components/Banner.vue'
import WaterFall from 'kuan-vue-waterfall'

export default defineComponent({
  name: 'Index',
  components: {
    Header,
    Banner,
    WaterFall
  },
  setup() {
    const state = reactive({
      images: [
        {
          src: 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/27.jpg',
          name: '捷安特自行车'
        },
        {
          src: 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/4.jpg',
          name: '联系刃9000'
        },
        {
          src: 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/2.jpg',
          name: 'surface go2'
        },
        {
          src: 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/14.jpg',
          name: 'Re:0'
        },
        {
          src: 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/1.jpg',
          name: '华为'
        },
        {
          src: 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/3.jpg',
          name: 'ROG冰刃双屏'
        },
        {
          src: 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/26.jpg',
          name: '雨伞'
        }
      ]
    })

    return {
      ...toRefs(state)
    }
  }
})
</script>

<style lang="less" scoped>
  @import "../styles/index.less";
</style>

<style lang="less">
  .kuan-vue-waterfall {
    width: 1200px !important;
  }
</style>
