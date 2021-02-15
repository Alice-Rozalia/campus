import request from '@/utils/request'

export const keepImageApi = ((data: any) => {
  return request({
    url: '/pri/image/keep',
    method: 'post',
    data: data
  })
})
