import request from '@/utils/request'

export const fetchLoginLogApi = ((query: object) => {
  return request({
    url: '/pri/log/login',
    method: 'get',
    params: query
  })
})
