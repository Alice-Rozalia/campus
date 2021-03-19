import { reactive } from 'vue'
import { fetchLoginLogApi } from '@/api/log'

const state = reactive({
  query: {
    page: 1,
    limit: 8
  },
  logs: [],
  total: 0
})

// 获取日志数据
const getLoginLogList = async () => {
  const { data } = await fetchLoginLogApi(state.query)
  if (data.success) {
    state.logs = data.data.loginLogs.items
    state.total = data.data.loginLogs.total
  }
}

// 分页
const handleCurrentChange = (val: number) => {
  state.query.page = val
  getLoginLogList()
}

export {
  handleCurrentChange,
  getLoginLogList,
  state
}
