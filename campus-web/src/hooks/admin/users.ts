import { reactive } from 'vue'
import { fetchUserListApi } from '@/api/user'

const state = reactive({
  query: {
    page: 1,
    limit: 8
  },
  users: [],
  total: 0
})

// 获取用户数据
const getUserList = async () => {
  const { data } = await fetchUserListApi(state.query)
  if (data.success) {
    state.users = data.data.users.items
    state.total = data.data.users.total
  }
}

// 分页
const handleCurrentChange = (val: number) => {
  state.query.page = val
  getUserList()
}

export {
  handleCurrentChange,
  getUserList,
  state
}
