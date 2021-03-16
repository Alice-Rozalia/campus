import request from '@/utils/request'

export const fetchLettersApi = ((query: object) => {
  return request({
    url: '/pri/message/letters',
    method: 'get',
    params: query
  })
})

export const fetchUnreadLettersApi = (() => {
  return request({
    url: '/pri/message/unreadLetter',
    method: 'get'
  })
})

export const fetchLetterDetailApi = ((id: string | string[], query: object) => {
  return request({
    url: '/pri/message/detail/' + id,
    method: 'get',
    params: query
  })
})

export const sendLetterApi = ((data: object) => {
  return request({
    url: '/pri/message/send',
    method: 'post',
    data
  })
})
