<script setup lang="ts">
import { onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import TopSection from '@/components/home/TopSection.vue'
import TitleSection from '@/components/home/TitleSection.vue'
import UserSection from '@/components/home/UserSection.vue'
import { getUserInfo } from '@/api/user'
import { useI18n } from 'vue-i18n'
import { useUserStore } from '@/stores/user'
import type { UserInfo } from '@/types/user'

const { t } = useI18n()
const userStore = useUserStore()

// 获取用户信息
const loadUserInfo = async () => {
  try {
    console.log('[HomeView] Starting to load user info')
    const response = await getUserInfo()
    console.log('[HomeView] User info response:', response)

    if (response.data.code === 200) {
      const userInfo = response.data.data as UserInfo
      console.log('[HomeView] User info loaded:', userInfo)
      // 存储用户信息到 store
      userStore.setUserInfo(userInfo)
    } else {
      console.error('[HomeView] Failed to load user info:', response.data)
      ElMessage.error(t('common.fetchUserInfoFailed'))
    }
  } catch (error) {
    console.error('[HomeView] Error loading user info:', error)
    if (error instanceof Error) {
      console.error('[HomeView] Error message:', error.message)
      console.error('[HomeView] Error stack:', error.stack)
    }
    ElMessage.error(t('common.fetchUserInfoFailed'))
  }
}

// 组件加载时获取用户信息
onMounted(() => {
  loadUserInfo()
})
</script>

<template>
  <el-row>
    <el-col :span="24">
      <TopSection class="top-section"/>
    </el-col>
  </el-row>
  <el-row>
    <TitleSection class="title-section"/>
  </el-row>
  <el-row>
    <UserSection class="user-section"/>
  </el-row>
</template>

<style scoped>
.user-section{
  width: 100%;
}
</style>
