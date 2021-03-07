import request from '@/utils/request'

export const addCommentApi = ((comment: object) => {
  return request({
    url: '/pri/comment',
    method: 'post',
    data: comment
  })
})

export const fetchCommentsApi = ((id: number, query: object) => {
  return request({
    url: '/pub/comment/' + id,
    method: 'get',
    params: query
  })
})
