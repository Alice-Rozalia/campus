<template>
  <div class="banner">
    <!-- 左侧菜单-->
    <div class="menu-content" @mouseleave="hideCategoryDetail">
      <div class="menu-item" v-for="item in categories" :key="item.id" @mouseenter="showCategoryDetail(item)">
        <span>{{ item.name }}</span>
        <i class="el-icon-caret-right"></i>
      </div>

      <!-- 详细分类 -->
      <div class="menu-detail" v-show="JSON.stringify(actionCategory) !== '{}'">
        <el-divider>{{ actionCategory.name }}</el-divider>

        <div class="menu-detail-list-content">
          <div class="menu-detail-items" v-for="val in actionCategory.children" :key="val.id">
            <img :src="val.image" alt="swiper">
            <p>{{ val.name }}</p>
          </div>
        </div>
      </div>
    </div>

    <!-- 右侧-->
    <div class="right-box">
      <div class="swiper">
        <el-carousel height="360px">
          <el-carousel-item v-for="item in 3" :key="item">
            <img src="https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/swiper/majo_bg_s01up01_03.png"
                 style="width: 100%;" alt="swiper"/>
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="order-box">
        <div class="tags">
          <el-tag>所有二手商品</el-tag>
        </div>
        <div class="order-item">
          <div class="active-order">时间</div>
          <div class="order-item-price">价格</div>
          <div>浏览量</div>
        </div>
      </div>

    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs, onUnmounted, ref } from 'vue'
import { fetchCategoriesApi } from '@/api/category'

export default defineComponent({
  name: 'Banner',
  setup() {
    const state = reactive({
      categories: [],
      actionCategory: {}
    })

    // 获取分类
    const getCategories = async () => {
      const cacheCategories = window.sessionStorage.getItem('categories')
      if (cacheCategories) {
        state.categories = JSON.parse(cacheCategories)
      } else {
        const { data } = await fetchCategoriesApi()
        if (data.success) {
          state.categories = data.data.categories
          window.sessionStorage.setItem('categories', JSON.stringify(data.data.categories))
        }
      }
    }

    onMounted(() => {
      getCategories()
    })

    // 显示分类详情
    const showCategoryDetail = (item: object) => {
      state.actionCategory = item
    }

    // 隐藏分类详情
    const hideCategoryDetail = () => {
      state.actionCategory = {}
    }

    return {
      ...toRefs(state),
      showCategoryDetail,
      hideCategoryDetail
    }
  }
})
</script>

<style lang="less" scoped>
@import "../styles/banner.less";
</style>
