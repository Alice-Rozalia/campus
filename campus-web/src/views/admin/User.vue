<template>
  <el-card>
    <el-table :data="users" style="width: 100%" border stripe>
      <el-table-column prop="username" label="用户名"/>
      <el-table-column prop="phone" label="手机号"/>
      <el-table-column prop="address" label="地址"/>
      <el-table-column prop="createTime" label="创建时间"/>
      <el-table-column prop="enabled" label="是否可用">
        <template #default="scope">
          <el-tag size="medium" v-if="scope.row.enabled">启用</el-tag>
          <el-tag size="medium" v-else type="danger">禁用</el-tag>
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
</template>

<script lang="ts">
import { defineComponent, toRefs, onMounted } from 'vue'
import { state, handleCurrentChange, getUserList } from '@/hooks/admin/users'

export default defineComponent({
  name: 'User',
  setup() {
    onMounted(() => {
      getUserList()
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
