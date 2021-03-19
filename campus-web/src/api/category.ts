import request from '@/utils/request'

export const fetchCategoriesApi = (() => {
  return request({
    url: '/pub/category',
    method: 'get'
  })
})
