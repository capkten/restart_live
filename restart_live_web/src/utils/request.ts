import axios from 'axios'
import type { AxiosInstance, AxiosError, InternalAxiosRequestConfig, AxiosResponse } from 'axios'

interface ResponseData<T = any> {
  code: number
  msg: string
  data?: T
}

// 创建 axios 实例
const request: AxiosInstance = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:10000/api/v1',
  timeout: 15000, // 请求超时时间
  headers: {
    'Content-Type': 'application/json;charset=UTF-8',
  },
})

// 请求拦截器
request.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    // 从 localStorage 获取 token
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }

    // 如果是 FormData，设置对应的 Content-Type
    if (config.data instanceof FormData) {
      config.headers['Content-Type'] = 'multipart/form-data;charset=UTF-8'
    }

    return config
  },
  (error: AxiosError) => {
    console.error('Request error:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  (response: AxiosResponse<ResponseData>) => {
    const { data, config } = response
    const url = config.url || ''

    // 如果code是200，说明请求成功，直接返回数据
    if (data.code === 200) {
      return data.data
    }

    // 处理401错误码
    if (data.code === 401) {
      // 对于登录和注册接口，不需要跳转
      if (!url.includes('/users/login') && !url.includes('/users/register')) {
        localStorage.removeItem('token')
        window.location.href = '/login'
      }
    }

    // 其他错误码统一处理
    return Promise.reject(new Error(data.msg || '请求失败'))
  },
  (error: AxiosError) => {
    // 处理网络错误、超时等
    const message = error.message || '网络错误'
    console.error('Request failed:', message)
    return Promise.reject(error)
  }
)

export default request
