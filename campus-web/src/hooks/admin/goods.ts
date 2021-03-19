import { reactive } from 'vue'
import { fetchAllGoodsApi } from '@/api/goods'

const state = reactive({
  query: {
    page: 1,
    limit: 8
  },
  goods: [],
  total: 0
})

// 获取商品数据
const getGoodsList = async () => {
  const { data } = await fetchAllGoodsApi(state.query)
  if (data.success) {
    state.goods = data.data.goods.items
    state.total = data.data.goods.total
  }
}

// 分页
const handleCurrentChange = (val: number) => {
  state.query.page = val
  getGoodsList()
}

export {
  handleCurrentChange,
  getGoodsList,
  state
}
