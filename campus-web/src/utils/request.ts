import axios, { AxiosRequestConfig, AxiosResponse } from 'axios'
import { ElMessage } from 'element-plus'
import NProgress from 'nprogress'

const isPrd: boolean = process.env.NODE_ENV === 'production'

const request = axios.create({
  baseURL: isPrd ? '' : 'http://localhost:8360/api/v1',
  timeout: 5000,
  headers: {
    Accept: 'application/json, text/plain, */*',
    'Content-Type': 'application/json',
    'X-Requested-With': 'XMLHttpRequest'
  }
})

request.interceptors.request.use((config: AxiosRequestConfig) => {
  NProgress.start()
  const token = window.sessionStorage.getItem('token')
  if (token) {
    config.headers.Authorization = token
  }
  return config
}, error => {
  return Promise.reject(error)
})

request.interceptors.response.use((config: AxiosResponse<any>) => {
  if (!config.data.success) {
    ElMessage.error(config.data.message || '服务器端异常！')
  }
  NProgress.done()
  return config
}, error => {
  if (error.response.status === 504 || error.response.status === 404) {
    ElMessage.error('服务器被吃了( ╯□╰ )！')
  } else if (error.response.status === 403) {
    ElMessage.error('权限不足！')
  } else {
    if (error.response.data.msg) {
      ElMessage.error(error.response.data.message)
    } else {
      ElMessage.error('未知错误！')
    }
  }
  return error
})

export default request
