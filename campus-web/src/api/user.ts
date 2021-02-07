import request from '@/utils/request'

export const loginApi = ((data: any) => {
  return request({
    url: `/pub/doLogin?username=${data._rawValue.username}&password=${data._rawValue.password}`,
    method: 'post'
  })
})
