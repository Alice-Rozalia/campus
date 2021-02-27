<template>
  <div class="detail-container">
    <Header/>

    <div class="goods-info">
      <div class="left-images">
        <el-carousel trigger="click" height="350px">
          <el-carousel-item v-for="item in goods.images" :key="item.id">
            <img :src="item.url" alt="goods">
          </el-carousel-item>
        </el-carousel>
      </div>

      <div class="detail-info">
        <div class="user-avatar">
          <el-avatar size="medium" :src="goods.avatar"/>
          <i>{{goods.username}}</i>
        </div>
        <h2 class="goods-title">{{goods.name}}</h2>
        <p>{{goods.introduce}}</p>
        <div class="price-row">
          <i class="el-icon-price-tag"></i>
          <div>
            <span class="goods-price">￥{{goods.price}}</span>
            <span>原价{{goods.original}}</span>
          </div>
        </div>
        <div>
          <i class="el-icon-time"></i>
          <span class="publish-time">{{goods.createDate}}</span>
        </div>
        <div class="location">
          <i class="el-icon-location-outline"></i>
          <span class="addr">{{goods.address}}</span>
        </div>
        <div>
          <i class="el-icon-collection-tag"></i>
          <span class="goods-category">{{goods.category}}</span>
        </div>
        <div class="talk">
          <el-button type="primary" size="mini">私聊</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs, onMounted } from 'vue'
import { fetchGoodsDetailByIdApi } from '@/api/goods'
import { useRoute, useRouter } from 'vue-router'
import Header from '@/components/Header.vue'

export default defineComponent({
  name: 'Detail',
  components: {
    Header
  },
  setup() {
    const route = useRoute()
    const router = useRouter()

    const state = reactive({
      goods: {}
    })

    onMounted(async () => {
      const id: number = Number(route.params.id)
      if (!id) {
        router.push('/')
      }
      const { data } = await fetchGoodsDetailByIdApi(id)
      if (data.success) {
        state.goods = data.data.goods
      }
    })

    return {
      ...toRefs(state)
    }
  }
})
</script>

<style lang="less" scoped>
  .detail-container {
    background-color: #f2f2f2;
    min-height: 100vh;
  }

  .goods-info {
    display: flex;
    width: 1000px;
    margin: 15px auto 20px;
    padding: 25px;
    background-color: #fff;
    border-radius: 6px;

    .left-images {
      width: 400px;
      margin-right: 50px;
      overflow: hidden;

      img {
        width: 100%;
      }
    }

    .detail-info {
      width: 550px;
      box-sizing: border-box;
      color: #616776;
      font-size: 14px;

      .user-avatar {
        display: flex;
        align-items: center;

        > i {
          color: #07111b;
          margin-left: 15px;
        }
      }

      .goods-title {
        color: #17233d;
        font-size: 16px;
        font-weight: 700;
        padding: 12px 0;
      }

      > p {
        color: #616776;
        line-height: 1.3;
        padding-right: 20px;
      }

      .price-row {
        display: flex;
        padding: 12px 0;
        color: #808695;

        .goods-price {
          color: #f01414;
          margin: 0 15px;
        }
      }

      .publish-time {
        margin-left: 15px;
      }

      .location {
        padding: 12px 0;

        .addr {
          margin-left: 15px;
        }
      }

      .goods-category {
        margin-left: 15px;
        color: #00a4ff;
      }

      .talk {
        margin-top: 15px;
        text-align: right;
      }
    }
  }
</style>
