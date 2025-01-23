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
  const formData = new FormData()
  formData.append('icon', iconName)

  return request({
    url: '/users/config/icon',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

// 上传背景图片
export function uploadBackgroundImage(file: File) {
  const formData = new FormData()
  formData.append('file', file)

  return request({
    url: '/users/config/bg_img',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}
