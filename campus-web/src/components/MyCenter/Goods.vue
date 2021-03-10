<template>
  <el-table :data="goodsList" border stripe style="width: 100%">
    <el-table-column prop="name" label="商品名"/>
    <el-table-column prop="price" label="价钱"/>
    <el-table-column prop="createDate" label="发布时间"/>
    <el-table-column prop="look" label="浏览量"/>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button @click="deleteMyGoods(scope.row.id)" type="danger" size="mini">删除</el-button>
        <router-link class="look" :to="'/detail/' + scope.row.id">查看</router-link>
      </template>
    </el-table-column>
  </el-table>

  <div class="footer-pagination" v-if="goodsList">
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
import { fetchMyGoodsApi, deleteMyGoodsByIdApi } from '@/api/goods'
import { successMessage } from '@/utils/message'

export default defineComponent({
  name: 'Goods',
  setup() {
    const state = reactive({
      goodsList: [],
      query: {
        page: 1,
        limit: 8
      },
      total: 0
    })

    onMounted(() => {
      getMyGoods()
    })

    // 获取我发布的商品
    const getMyGoods = async () => {
      const { data } = await fetchMyGoodsApi(state.query)
      if (data.success) {
        state.goodsList = data.data.myGoods.items
        state.total = data.data.myGoods.total
      }
    }

    // 分页
    const handlePageChange = (page: number) => {
      state.query.page = page
      getMyGoods()
    }

    // 删除商品
    const deleteMyGoods = async (id: number) => {
      const { data } = await deleteMyGoodsByIdApi(id)
      if (data.success) {
        successMessage(data.message)
        getMyGoods()
      }
    }

    return {
      ...toRefs(state),
      handlePageChange,
      deleteMyGoods
    }
  }
})
</script>

<style lang="less" scoped>
.look {
  color: #00a4ff;
  padding-left: 12px;
  font-size: 14px;
}

.footer-pagination {
  text-align: center;
  padding-top: 15px;
}
</style>
