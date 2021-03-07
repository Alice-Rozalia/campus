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

export const adminLoginApi = ((data: any) => {
  return request({
    url: '/pub/user/admin_login',
    method: 'post',
    data
  })
})
