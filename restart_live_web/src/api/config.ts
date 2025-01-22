import request from '@/utils/request'

// 获取用户配置，包括背景图和图标
export function getUserConfig() {
  return request({
    url: '/users/config',
    method: 'get'
  })
}

// 保存用户选择的图标
export function saveUserIcon(iconName: string) {
  return request({
    url: '/api/v1/users/icon',
    method: 'post',
    data: {
      iconName
    }
  })
}
