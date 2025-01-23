import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { UserInfo } from '@/types/user'

export const useUserStore = defineStore('user', () => {
  const userInfo = ref<UserInfo | null>(null)

  // 设置用户信息
  function setUserInfo(info: UserInfo) {
    userInfo.value = info
  }

  // 清除用户信息
  function clearUserInfo() {
    userInfo.value = null
  }

  return {
    userInfo,
    setUserInfo,
    clearUserInfo
  }
})
