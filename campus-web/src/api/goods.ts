import request from '@/utils/request'

export const publishGoodsApi = ((data: any) => {
  data._rawValue.categoryId = data._rawValue.categoryId[1]
  return request({
    url: '/pri/goods/publish',
    method: 'post',
    data: data._rawValue
  })
})

export const fetchIndexGoodsApi = ((query: object) => {
  return request({
    url: '/pub/goods/index',
    method: 'get',
    params: query
  })
})

export const fetchGoodsDetailByIdApi = ((id: number) => {
  return request({
    url: '/pub/goods/' + id,
    method: 'get'
  })
})
