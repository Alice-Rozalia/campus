import request from '@/utils/request'

export const publishGoodsApi = ((data: any) => {
  data._rawValue.categoryId = data._rawValue.categoryId[1]
  return request({
    url: '/pri/goods/publish',
    method: 'post',
    data: data._rawValue
  })
})