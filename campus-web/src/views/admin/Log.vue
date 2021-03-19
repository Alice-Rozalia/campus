<template>
  <el-card class="box-card">
    <el-table :data="logs" style="width: 100%" border stripe>
      <el-table-column prop="username" label="登录用户"/>
      <el-table-column prop="loginTime" label="登录时间"/>
      <el-table-column prop="location" label="登录地址"/>
      <el-table-column prop="ip" label="登录ip"/>
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
import { state, getLoginLogList, handleCurrentChange } from '@/hooks/admin/log'

export default defineComponent({
  name: 'Log',
  setup() {
    onMounted(() => {
      getLoginLogList()
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
