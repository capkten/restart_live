import request from '@/utils/request'

interface LoginResponse {
  token: string
}

interface LoginData {
  username: string
  password: string
}

interface RegisterData extends LoginData {
  confirmPassword?: string
}

export const login = (data: LoginData) => {
  const formData = new FormData()
  formData.append('username', data.username)
  formData.append('password', data.password)

  return request<LoginResponse>({
    url: '/users/login',
    method: 'post',
    data: formData
  })
}

export const register = (data: LoginData) => {
  const formData = new FormData()
  formData.append('username', data.username)
  formData.append('password', data.password)

  return request({
    url: '/users/register',
    method: 'post',
    data: formData
  })
}

export const logout = () => {
  return request({
    url: '/auth/logout',
    method: 'post'
  })
}

// 获取用户信息
export const getUserInfo = () => {
  return request({
    url: '/auth/user-info',
    method: 'get'
  })
}
