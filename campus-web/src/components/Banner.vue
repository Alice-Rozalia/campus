<template>
  <div class="banner">
    <!-- 左侧菜单-->
    <div class="menu-content">
      <div class="menu-item" v-for="item in categories" :key="item.id">
        <span>{{item.name}}</span>
        <i class="el-icon-caret-right"></i>
      </div>
    </div>

    <!-- 右侧-->
    <div class="right-box">
      <div class="swiper">
        <el-carousel height="360px">
          <el-carousel-item v-for="item in 3" :key="item">
            <img src="https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/swiper/majo_bg_s01up01_03.png" style="width: 100%;" />
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="order-box">
        <div class="tags">
          <el-tag>所有二手商品</el-tag>
        </div>
        <div class="order-item">
          <div class="active-order">时间<i class="el-icon-top"></i></div>
          <div class="order-item-price">价格</div>
          <div>浏览量</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs } from 'vue'
import { fetchCategoriesApi } from '@/api/category'

export default defineComponent({
  name: 'Banner',
  setup() {
    const state = reactive({
      categories: []
    })
    const getCategories = async () => {
      const { data } = await fetchCategoriesApi()
      if (data.success) {
        state.categories = data.data.categories
      }
    }

    onMounted(() => {
      getCategories()
    })

    return {
      ...toRefs(state)
    }
  }
})
</script>

<style lang="less" scoped>
  @import "../styles/banner.less";
</style>
