<template>
  <ul>
    <li class="follow-list" v-for="item in followList" :key="item.goods.id">
      <router-link :to="'/detail/' + item.goods.id">{{ item.goods.name }}</router-link>
      <span class="price">￥{{ item.goods.price }}</span>
      <span class="follow-time">收藏于 {{ dateFormat(item.followTime) }}</span>
    </li>
  </ul>

  <div class="footer-pagination" v-if="followList">
    <el-pagination
      layout="prev, pager, next"
      :total="total"
      :page-size="query.limit"
      @current-change="handlePageChange"
    />
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs } from 'vue'
import { fetchFollowListApi } from '@/api/follow'
import dateFormat from '@/utils/format'

export default defineComponent({
  name: 'Follow',
  setup() {
    const state = reactive({
      followList: [],
      query: {
        page: 1,
        limit: 8
      },
      total: 0
    })

    onMounted(() => {
      getFollowList()
    })

    // 获取收藏列表
    const getFollowList = async () => {
      const { data } = await fetchFollowListApi(state.query)
      if (data.success) {
        state.followList = data.data.followees
        state.total = data.data.followeeCount
      }
    }

    // 分页
    const handlePageChange = (page: number) => {
      state.query.page = page
      getFollowList()
    }

    return {
      ...toRefs(state),
      dateFormat,
      handlePageChange
    }
  }
})
</script>

<style lang="less" scoped>
.follow-list {
  padding: 15px 0;
  border-bottom: 1px solid #ccc;

  > a {
    color: #00a4ff;
    font-size: 18px;
  }

  .price {
    font-size: 12px;
    color: #f01414;
    margin: 0 15px;
  }

  .follow-time {
    float: right;
    font-size: 13px;
    color: #93999f;
  }
}

.footer-pagination {
  text-align: center;
  padding-top: 15px;
}
</style>
