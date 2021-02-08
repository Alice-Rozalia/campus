import request from '@/utils/request'

export const loginApi = ((data: any) => {
  return request({
    url: `/pub/doLogin?username=${data.username}&password=${data.password}`,
    method: 'post'
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
