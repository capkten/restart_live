import { createI18n } from 'vue-i18n'

// 英文翻译
const en = {
  common: {
    fetchUserInfoFailed: 'Failed to fetch user information'
  },
  titleSection: {
    title: 'Life Restart Plan'
  },
  topSection: {
    changeCover: 'Change Cover',
    upload: 'Upload',
    uploadFile: 'Upload File',
    fileSizeLimit: 'File size should not exceed 5MB.',
    uploadSuccess: 'Cover updated successfully',
    uploadFailed: 'Upload failed, please try again',
    imageOnly: 'Only image files are allowed!',
    sizeExceeded: 'Image size cannot exceed 5MB!',
    gallery: 'Gallery',
    link: 'Link',
    searchIcon: 'Search icons...',
    iconUpdateSuccess: 'Icon updated successfully',
    iconUpdateFailed: 'Failed to update icon'
  }
}

// 中文翻译
const zh = {
  common: {
    fetchUserInfoFailed: '获取用户信息失败'
  },
  titleSection: {
    title: '人生重启计划'
  },
  topSection: {
    changeCover: '更改封面',
    upload: '上传',
    uploadFile: '上传文件',
    fileSizeLimit: '每个文件的大小不超过 5MB。',
    uploadSuccess: '封面更新成功',
    uploadFailed: '上传失败，请重试',
    imageOnly: '只能上传图片文件!',
    sizeExceeded: '图片大小不能超过 5MB!',
    gallery: '画廊',
    link: '链接',
    searchIcon: '搜索图标...',
    iconUpdateSuccess: '图标保存成功',
    iconUpdateFailed: '图标保存失败'
  }
}

// 创建 i18n 实例
const i18n = createI18n({
  legacy: false, // 使用 Composition API 模式
  locale: 'zh', // 默认语言
  fallbackLocale: 'en', // 备用语言
  messages: {
    en,
    zh
  }
})

export default i18n
