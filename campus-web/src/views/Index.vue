<template>
  <div class="main-container">
    <Header/>

    <div class="main-warp">
      <Banner/>

      <!--商品列表盒子-->
      <div class="goods" v-if="goods.length">

        <!-- 列表 -->
        <div class="goods-list">
          <!-- row 列 -->
          <div v-for="(row,index) in goods" :key="index">
            <!-- 每列的商品 -->
            <div v-for="item in row" :key="item.id + item.name" class="card" @click="goDetailPage(item)">
              <img :src="item.cover" alt="商品图片"/>
              <div class="card-info">
                <p class="goods-name">{{ item.name }}</p>
                <div class="goods-publish">
                  <span><i class="el-icon-time"></i> {{ item.createDate }}</span>
                  <span><i class="el-icon-view"></i> {{ item.look }}</span>
                </div>
                <div class="introduce">{{ item.introduce }}</div>
                <div class="goods-price-addr">
                  <span class="goods-price">￥{{ item.price }}</span>
                  <span class="goods-addr">{{ item.category }}</span>
                </div>
              </div>
              <div class="di"></div>
            </div>
          </div>
        </div>

        <div class="load-more">
          <el-pagination
            background
            layout="prev, pager, next"
            :total="total"
            :current-page="page"
            :page-size="limit"
            @current-change="currentPageChange"
          >
          </el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, computed, toRefs } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
import Header from '@/components/Header.vue'
import Banner from '@/components/Banner.vue'

export default defineComponent({
  name: 'Index',
  components: {
    Header,
    Banner
  },
  setup() {
    const router = useRouter()
    const store = useStore()
    const state = useStore<GlobalDataProps>()

    const set = reactive({
      goods: computed(() => state.state.goods.data),
      total: computed(() => state.state.goods.total),
      page: computed(() => state.state.query.page),
      limit: computed(() => state.state.query.limit)
    })

    onMounted(() => {
      store.dispatch('getIndexGoods')
    })

    // 分页
    const currentPageChange = (newPage: number) => {
      store.dispatch('pageChange', newPage)
    }

    // 前往商品详情页
    const goDetailPage = (item: any) => {
      router.push('/detail/' + item.id)
    }

    return {
      ...toRefs(set),
      currentPageChange,
      goDetailPage
    }
  }
})
</script>

<style lang="less" scoped>
@import "../styles/index.less";
</style>
