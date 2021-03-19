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
          <div @click="getGoodsByCategory(val.id, val.name)" class="menu-detail-items"
               v-for="val in actionCategory.children" :key="val.id">
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
          <el-carousel-item>
            <introduce/>
          </el-carousel-item>
          <el-carousel-item>
            <introduce2/>
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="order-box">
        <div class="tags">
          <el-tag v-text="searchText"></el-tag>
        </div>
        <div class="order-item">
          <div :class="active === 1 ? `active-order ${className}` : ''"
               @click="getIndexGoodsBySort('create_date', 1)">时间
          </div>
          <div :class="active === 2 ? `active-order ${className}` : ''" class="order-item-price"
               @click="getIndexGoodsBySort('price', 2)">价格
          </div>
          <div :class="active === 3 ? `active-order ${className}` : ''"
               @click="getIndexGoodsBySort('look', 3)">浏览量
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs } from 'vue'
import { fetchCategoriesApi } from '@/api/category'
import { useStore } from 'vuex'
import Introduce from '@/components/Introduce.vue'
import Introduce2 from '@/components/Introduce2.vue'

export default defineComponent({
  name: 'Banner',
  components: {
    Introduce,
    Introduce2
  },
  setup() {
    const store = useStore()

    const state = reactive({
      categories: [],
      actionCategory: {},
      active: 1,
      order: 'ASC',
      className: 'el-icon-bottom',
      searchText: '所有二手商品'
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

    // 根据时间、价钱、浏览量排序获取商品
    const getIndexGoodsBySort = (sort: string, ac: number) => {
      state.order === 'ASC' ? state.order = 'DESC' : state.order = 'ASC'
      state.order === 'ASC' ? state.className = 'el-icon-top' : state.className = 'el-icon-bottom'
      state.active = ac
      store.dispatch('sortChange', { sort, order: state.order })
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

    // 根据分类查询商品
    const getGoodsByCategory = (id: number, name: string) => {
      state.searchText = name
      store.dispatch('getIndexGoodsByCategoryId', id)
    }

    return {
      ...toRefs(state),
      showCategoryDetail,
      hideCategoryDetail,
      getIndexGoodsBySort,
      getGoodsByCategory
    }
  }
})
</script>

<style lang="less" scoped>
@import "../styles/banner.less";
</style>
