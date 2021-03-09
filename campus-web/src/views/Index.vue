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
            :current-page="query.page"
            :page-size="query.limit"
            @current-change="currentPageChange"
          >
          </el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs, onMounted } from 'vue'
import { fetchIndexGoodsApi } from '@/api/goods'
import Header from '@/components/Header.vue'
import Banner from '@/components/Banner.vue'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'Index',
  components: {
    Header,
    Banner
  },
  setup() {
    const state = reactive({
      goods: [],
      total: 0,
      query: { page: 1, limit: 8 }
    })

    const router = useRouter()

    const getIndexGoods = async () => {
      const { data } = await fetchIndexGoodsApi(state.query)
      if (data.success) {
        const items = data.data.goods.items
        state.total = data.data.goods.total
        console.log(data.data.goods.items)

        const num = Math.ceil(items.length / 4)

        let result = items.map((item: any, index: number, array: []) => {
          if (index < 4) {
            return array.slice(index * num, (index + 1) * num)
          } else {
            return false
          }
        })

        result = JSON.stringify(result.filter((item: any) => item))
        state.goods = JSON.parse(result)
      }
    }

    onMounted(() => {
      getIndexGoods()
    })

    const currentPageChange = (newPage: number) => {
      state.query.page = newPage
      getIndexGoods()
    }

    const goDetailPage = (item: any) => {
      router.push('/detail/' + item.id)
    }

    return {
      ...toRefs(state),
      currentPageChange,
      goDetailPage
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
