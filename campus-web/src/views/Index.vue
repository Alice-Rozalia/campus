<template>
  <div class="main-container">
    <Header/>

    <div class="main-warp">
      <Banner/>

      <div class="goods" v-if="goods.length">
        <water-fall width="290px" gap="10px" :delay="true" :data="goods">
          <template #default="item">
            <div class="card">
              <img :src="item.cover" alt="商品图片"/>
              <div class="card-info">
                <p class="goods-name">{{item.name}}</p>
                <div class="goods-publish">
                  <span class="el-icon-time">{{item.createDate}}</span>
                  <span class="el-icon-view">{{item.look}}</span>
                </div>
                <div class="introduce">{{item.introduce}}</div>
                <div class="goods-price-addr">
                  <span class="goods-price">￥{{item.price}}</span>
                  <span class="goods-addr">{{item.category}}</span>
                </div>
              </div>
            </div>
          </template>
        </water-fall>


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
      goods: [],
      total: 0,
      query: { page: 1, limit: 8 }
    })

    const getIndexGoods = async () => {
      const { data } = await fetchIndexGoodsApi(state.query)
      if (data.success) {
        state.goods = data.data.goods.items
        state.total = data.data.goods.total
      }
    }

    onMounted(() => {
      getIndexGoods()
    })

    const currentPageChange = (newPage: number) => {
      state.query.page = newPage
      getIndexGoods()
    }

    return {
      ...toRefs(state),
      currentPageChange
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
