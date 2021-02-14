import request from '@/utils/request'

export const loginApi = ((data: any) => {
  return request({
    url: '/pub/user/login',
    method: 'post',
    data
  })
})

export const registerApi = ((data: any) => {
  return request({
    url: '/pub/user/register',
    method: 'post',
    data: data
  })
})

export const logoutApi = (() => {
  return request({
    url: '/pri/logout',
    method: 'get'
  })
})
