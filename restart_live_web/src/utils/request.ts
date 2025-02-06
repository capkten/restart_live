import axios from 'axios'
import type { AxiosInstance, AxiosError, InternalAxiosRequestConfig, AxiosResponse } from 'axios'
import router from '@/router'

interface ResponseData<T = Record<string, unknown>> {
  code: number
  msg: string
  data?: T
}

// 创建 axios 实例
const request: AxiosInstance = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:11000/api/v1',
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
    console.log('[Request Interceptor] URL:', config.url)
    console.log('[Request Interceptor] Token exists:', !!token)

    // 排除登录和注册接口
    if (token && !config.url?.includes('/login') && !config.url?.includes('/register')) {
      config.headers.Authorization = `${token}`
      console.log('[Request Interceptor] Added Authorization header')
    }

    // 如果是 FormData，设置对应的 Content-Type
    if (config.data instanceof FormData) {
      config.headers['Content-Type'] = 'multipart/form-data;charset=UTF-8'
    }

    return config
  },
  (error: AxiosError) => {
    console.error('[Request Error]', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  (response: AxiosResponse<ResponseData>) => {
    const { data, config } = response
    const url = config.url || ''
    console.log('[Response Interceptor] URL:', url)
    console.log('[Response Interceptor] Response:', data)

    // 如果code是200，说明请求成功，直接返回数据
    if (data.code === 200) {
      console.log('[Response Interceptor] Success response')
      return response
    }

    // 处理401错误码
    if (data.code === 401) {
      console.log('[Response Interceptor] Received 401 error')
      // 对于登录和注册接口，不需要跳转
      if (!url.includes('/users/login') && !url.includes('/users/register')) {
        console.log('[Response Interceptor] Not login/register route, removing token and redirecting')
        localStorage.removeItem('token')
        router.push('/login')
      } else {
        console.log('[Response Interceptor] Login/register route, skipping redirect')
      }
    }

    // 其他错误码，返回错误信息
    console.log('[Response Interceptor] Other error:', data.msg)
    return Promise.reject(new Error(data.msg || 'Error'))
  },
  (error: AxiosError) => {
    console.error('[Response Error] Full error:', error)
    console.error('[Response Error] Response data:', error.response?.data)
    console.error('[Response Error] Request config:', error.config)
    return Promise.reject(error)
  }
)

export default request
