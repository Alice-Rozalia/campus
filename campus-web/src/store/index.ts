import { createStore, Commit } from 'vuex'
import { fetchIndexGoodsApi, fetchIndexGoodsByCategoryIdApi } from '@/api/goods'
import arrFormat from '@/utils/arrange'

export interface GoodsProps {
  id: number;
  category: string;
  cover: string;
  createDate: string;
  introduce: string;
  look: number;
  name: string;
  price: number;
  original: number;
}

export interface GlobalDataProps {
  token: string;
  goods: { data: Array<GoodsProps>; total: number; },
  query: { page: number, limit: number, sort: string, order: string, key: string | null }
}

const fetchIndexGoodsList = async (query: object, mutationName: string, commit: Commit) => {
  const { data } = await fetchIndexGoodsApi(query)
  if (data.success) {
    const items = data.data.goods.items
    const total = data.data.goods.total
    const result = arrFormat(items)

    commit(mutationName, { total, result })
  }
}

export default createStore<GlobalDataProps>({
  state: {
    token: window.sessionStorage.getItem('token') || '',
    goods: { data: [], total: 0 },
    query: { page: 1, limit: 8, sort: 'create_date', order: 'DESC', key: null }
  },
  mutations: {
    setGoodsList(state, { total, result }) {
      state.goods.total = total
      state.goods.data = JSON.parse(result)
    },
    setPage(state, page) {
      state.query.page = page
    },
    setSort(state, { sort, order }) {
      state.query.sort = sort
      state.query.order = order
    },
    setKey(state, key) {
      state.query.key = key
    }
  },
  actions: {
    getIndexGoods({ state, commit }) {
      fetchIndexGoodsList(state.query, 'setGoodsList', commit)
    },
    pageChange({ state, commit }, page) {
      commit('setPage', page)
      fetchIndexGoodsList(state.query, 'setGoodsList', commit)
    },
    sortChange({ state, commit }, { sort, order }) {
      commit('setSort', { sort, order })
      fetchIndexGoodsList(state.query, 'setGoodsList', commit)
    },
    keyChange({ state, commit }, key) {
      commit('setKey', key)
      fetchIndexGoodsList(state.query, 'setGoodsList', commit)
    },
    getIndexGoodsByCategoryId({ state, commit }, id) {
      fetchIndexGoodsByCategoryIdApi({ page: 1, limit: 12 }, id).then(({ data }) => {
        if (data.success) {
          const items = data.data.goods.items
          const total = data.data.goods.total
          const result = arrFormat(items)
          commit('setGoodsList', { total, result })
        }
      })
    }
  },
  modules: {}
})
