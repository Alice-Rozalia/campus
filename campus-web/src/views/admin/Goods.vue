<template>
  <el-card>
    <el-card>
      <el-table :data="goods" style="width: 100%" border stripe>
        <el-table-column prop="name" label="商品名"/>
        <el-table-column prop="price" label="单价"/>
        <el-table-column prop="original" label="原价"/>
        <el-table-column prop="createDate" label="发布时间"/>
        <el-table-column prop="enabled" label="是否被物主删除">
          <template #default="scope">
            <el-tag size="medium" v-if="scope.row.enabled">在售</el-tag>
            <el-tag size="medium" v-else type="danger">已删除</el-tag>
          </template>
        </el-table-column>
      </el-table>

      <div class="foot-pagination">
        <el-pagination
          background
          @current-change="handleCurrentChange"
          :current-page="query.page"
          :page-size="query.limit"
          layout="prev, pager, next"
          :total="total">
        </el-pagination>
      </div>
    </el-card>
  </el-card>
</template>

<script lang="ts">
import { defineComponent, toRefs, onMounted } from 'vue'
import { state, getGoodsList, handleCurrentChange } from '@/hooks/admin/goods'

export default defineComponent({
  name: 'Goods',
  setup() {
    onMounted(() => {
      getGoodsList()
    })

    return {
      ...toRefs(state),
      handleCurrentChange
    }
  }
})
</script>

<style scoped>
.foot-pagination {
  padding-top: 15px;
  text-align: center;
}
</style>
