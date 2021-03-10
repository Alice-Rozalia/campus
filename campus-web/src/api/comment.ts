import request from '@/utils/request'

export const addCommentApi = ((comment: object, id: number) => {
  return request({
    url: '/pri/comment/' + id,
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
