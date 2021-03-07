import request from '@/utils/request'

export const fetchLettersApi = ((query: object) => {
  return request({
    url: '/pri/message/letters',
    method: 'get',
    params: query
  })
})
