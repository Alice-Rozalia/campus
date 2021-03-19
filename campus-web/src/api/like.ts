import request from '@/utils/request'

export const giveLikeApi = ((query: object) => {
  return request({
    url: '/pri/give/like',
    method: 'post',
    params: query
  })
})
