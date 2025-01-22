import request from '@/utils/request'

// 获取用户保存的图标
export function getUserIcon() {
  return request({
    url: '/api/v1/users/icon',
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
