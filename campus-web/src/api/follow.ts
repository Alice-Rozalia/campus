import request from '@/utils/request'

export const hasFollowApi = ((id: number) => {
  return request({
    url: '/pri/follow/has/' + id,
    method: 'get'
  })
})

export const followInterestApi = ((id: number) => {
  return request({
    url: '/pri/follow/' + id,
    method: 'post'
  })
})

export const unfollowInterestApi = ((id: number) => {
  return request({
    url: '/pri/unfollow/' + id,
    method: 'post'
  })
})

export const fetchFollowListApi = ((query: object) => {
  return request({
    url: '/pri/follow/list',
    method: 'get',
    params: query
  })
})
