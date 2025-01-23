<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import { ElPopover, ElIcon, ElMessage } from 'element-plus'
import { useI18n } from 'vue-i18n'
import 'element-plus/dist/index.css'
import { getUserConfig, saveUserIcon, uploadBackgroundImage } from '@/api/config'

const { t } = useI18n()

// 背景图路径
const topImage = ref('')
// 控制更改封面按钮的显示
const showChangeButton = ref(false)
// 控制上传框的显示
const showUploadPopover = ref(false)

type IconComponent = typeof ElementPlusIconsVue.Monitor

const showIconSelector = ref(false)
const currentIcon = ref<IconComponent>(ElementPlusIconsVue.Monitor)

// Convert the icons object to an array and filter out non-component entries
const icons = Object.entries(ElementPlusIconsVue)
  .filter(([key]) => key !== 'default')
  .map(([key, component]) => ({
    name: key,
    component: component as IconComponent
  }))

// Optional: Add search functionality
const searchQuery = ref('')
const filteredIcons = computed(() => {
  if (!searchQuery.value) return icons
  return icons.filter(icon =>
    icon.name.toLowerCase().includes(searchQuery.value.toLowerCase())
  )
})

// 从后端加载配置，包括背景图和图标
const loadUserConfig = async () => {
  try {
    console.log('[TopSection] Starting to load user config')
    const response = await getUserConfig()
    console.log('[TopSection] Received response:', response)

    if (response.data.code === 200) {
      const config = response.data.data
      console.log('[TopSection] Config data:', config)
      topImage.value = config.topBgImg
      console.log('[TopSection] Set background image:', config.topBgImg)

      const savedIcon = icons.find(icon => icon.name === config.topBgIcon)
      console.log('[TopSection] Found saved icon:', savedIcon?.name)

      if (savedIcon) {
        currentIcon.value = savedIcon.component
        console.log('[TopSection] Set current icon:', savedIcon.name)
      }
    } else {
      console.error('[TopSection] Failed to load user config:', {
        code: response.data.code,
        msg: response.data.msg,
        data: response.data.data
      })
    }
  } catch (error) {
    console.error('[TopSection] Error loading user config:', error)
    if (error instanceof Error) {
      console.error('[TopSection] Error message:', error.message)
      console.error('[TopSection] Error stack:', error.stack)
    }
  }
}

// 保存选择的图标到后端
const selectIcon = async (icon: { name: string; component: IconComponent }) => {
  try {
    console.log('[TopSection] Saving icon:', icon.name)
    const response = await saveUserIcon(icon.name)
    console.log('[TopSection] Save response:', response)

    if (response.data.code === 200) {
      // 更新配置
      const config = response.data.data
      console.log('[TopSection] New config:', config)

      // 更新背景图
      if (config.topBgImg) {
        topImage.value = config.topBgImg
      }

      // 更新图标
      currentIcon.value = icon.component
      showIconSelector.value = false
      ElMessage.success(t('topSection.iconUpdateSuccess'))
    } else {
      throw new Error(response.data.msg || t('topSection.iconUpdateFailed'))
    }
  } catch (error) {
    console.error('[TopSection] Failed to save icon:', error)
    if (error instanceof Error) {
      console.error('[TopSection] Error message:', error.message)
      console.error('[TopSection] Error stack:', error.stack)
    }
    ElMessage.error(t('topSection.iconUpdateFailed'))
  }
}

// 处理图片上传前的验证
const beforeUpload = (file: File) => {
  const isImage = file.type.startsWith('image/')
  const isLt5M = file.size / 1024 / 1024 < 5

  if (!isImage) {
    ElMessage.error(t('topSection.imageOnly'))
    return false
  }
  if (!isLt5M) {
    ElMessage.error(t('topSection.sizeExceeded'))
    return false
  }
  return true
}

// 处理文件上传
const handleFileUpload = async (file: File) => {
  try {
    console.log('[TopSection] Starting to upload file:', file.name)
    const response = await uploadBackgroundImage(file)
    console.log('[TopSection] Upload response:', response)

    if (response.data.code === 200) {
      // 更新所有配置
      const config = response.data.data
      console.log('[TopSection] New config:', config)

      // 更新背景图
      if (config.topBgImg) {
        topImage.value = config.topBgImg
        console.log('[TopSection] Updated background image:', config.topBgImg)
      }

      // 更新图标
      if (config.topBgIcon) {
        const savedIcon = icons.find(icon => icon.name === config.topBgIcon)
        if (savedIcon) {
          currentIcon.value = savedIcon.component
          console.log('[TopSection] Updated icon:', config.topBgIcon)
        }
      }

      showUploadPopover.value = false
      ElMessage.success(t('topSection.uploadSuccess'))
    } else {
      throw new Error(response.data.msg || t('topSection.uploadFailed'))
    }
  } catch (error) {
    console.error('[TopSection] Upload failed:', error)
    if (error instanceof Error) {
      console.error('[TopSection] Error message:', error.message)
      console.error('[TopSection] Error stack:', error.stack)
    }
    ElMessage.error(t('topSection.uploadFailed'))
  }
}

// 处理文件选择
const handleFileSelect = (e: Event) => {
  const input = e.target as HTMLInputElement
  if (input.files && input.files[0]) {
    const file = input.files[0]
    if (beforeUpload(file)) {
      handleFileUpload(file)
    }
    // 清空 input 的值，允许重复上传同一个文件
    input.value = ''
  }
}

// 处理拖拽上传
const handleDrop = (e: DragEvent) => {
  e.preventDefault()
  const files = e.dataTransfer?.files
  if (files && files[0]) {
    const file = files[0]
    if (beforeUpload(file)) {
      handleFileUpload(file)
    }
  }
}

const handleDragOver = (e: DragEvent) => {
  e.preventDefault()
}

// 组件加载时获取用户配置
onMounted(() => {
  loadUserConfig()
})
</script>

<template>
  <div class="image-container" @mouseenter="showChangeButton = true" @mouseleave="showChangeButton = false">
    <img :src="topImage" alt="顶部图片" class="top-image">
    <ElPopover
      v-model:visible="showUploadPopover"
      trigger="click"
      :width="400"
      placement="bottom-end"
    >
      <template #reference>
        <div v-show="showChangeButton" class="change-cover-button">
          <ElIcon :size="16" class="button-icon">
            <Edit />
          </ElIcon>
          {{ t('topSection.changeCover') }}
        </div>
      </template>
      <div class="upload-dialog">
        <div class="upload-header">
          <div class="tab-group">
            <div class="tab active">{{ t('topSection.upload') }}</div>
          </div>
          <div class="close-button">
            <ElIcon :size="20" @click="showUploadPopover = false">
              <Close />
            </ElIcon>
          </div>
        </div>
        <div class="upload-body">
          <div
            class="upload-content"
            @drop="handleDrop"
            @dragover="handleDragOver"
          >
            <input
              type="file"
              accept="image/*"
              class="file-input"
              @change="handleFileSelect"
            >
            <ElIcon :size="32">
              <Upload />
            </ElIcon>
            <div class="upload-text">
              <span class="upload-title">{{ t('topSection.uploadFile') }}</span>
              <p class="upload-tip">{{ t('topSection.fileSizeLimit') }}</p>
            </div>
          </div>
        </div>
      </div>
    </ElPopover>
    <ElPopover
      v-model:visible="showIconSelector"
      trigger="click"
      :width="360"
      placement="top"
    >
      <template #reference>
        <div class="icon-container">
          <ElIcon :size="24">
            <component :is="currentIcon" />
          </ElIcon>
        </div>
      </template>
      <div class="icon-selector">
        <div class="search-box">
          <ElIcon class="search-icon" :size="16">
            <Search />
          </ElIcon>
          <input
            v-model="searchQuery"
            type="text"
            :placeholder="t('topSection.searchIcon')"
            class="search-input"
          >
        </div>
        <div class="icon-search-container">
          <div
            v-for="icon in filteredIcons"
            :key="icon.name"
            class="selectable-icon"
            @click="selectIcon(icon)"
          >
            <ElIcon :size="20">
              <component :is="icon.component" />
            </ElIcon>
            <span class="icon-name">{{ icon.name }}</span>
          </div>
        </div>
      </div>
    </ElPopover>
  </div>
</template>

<style scoped>
.image-container {
  width: 100vw;
  height: 300px;
  margin: 0;
  padding: 0;
  position: relative;
  overflow: hidden;
}

.top-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
  margin: 0;
  padding: 0;
}

.icon-container {
  position: absolute;
  left: 50px;
  bottom: 20px;
  background: rgba(0, 0, 0, 0.5);
  border-radius: 8px;
  padding: 8px;
  cursor: pointer;
  color: white;
}

.icon-selector {
  max-height: 400px;
  overflow-y: auto;
}

.search-box {
  position: sticky;
  top: 0;
  background: white;
  padding: 12px;
  border-bottom: 1px solid #eee;
  display: flex;
  align-items: center;
  gap: 8px;
}

.search-input {
  width: 100%;
  padding: 8px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  outline: none;
  font-size: 14px;
}

.search-input:focus {
  border-color: #409EFF;
}

.icon-search-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 12px;
  padding: 12px;
}

.selectable-icon {
  cursor: pointer;
  padding: 12px 8px;
  border-radius: 4px;
  transition: all 0.3s;
  color: #666;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 4px;
}

.icon-name {
  font-size: 12px;
  color: #666;
  text-align: center;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 100%;
}

.selectable-icon:hover {
  background-color: #f0f0f0;
  color: #409EFF;
  transform: scale(1.05);
}

.selectable-icon:hover .icon-name {
  color: #409EFF;
}

.change-cover-button {
  position: absolute;
  top: 20px;
  right: 20px;
  background: rgba(0, 0, 0, 0.6);
  color: white;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 4px;
  font-size: 14px;
  transition: background-color 0.3s;
}

.change-cover-button:hover {
  background: rgba(0, 0, 0, 0.8);
}

.button-icon {
  opacity: 0.8;
}

.upload-dialog {
  background: white;
  border-radius: 8px;
  overflow: hidden;
}

.upload-header {
  padding: 12px 16px;
  border-bottom: 1px solid #eee;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.tab-group {
  display: flex;
  gap: 16px;
}

.tab {
  font-size: 14px;
  color: #666;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 4px;
}

.tab.active {
  color: #333;
  font-weight: 500;
}

.unsplash-icon {
  width: 16px;
  height: 16px;
}

.close-button {
  cursor: pointer;
  color: #999;
  padding: 4px;
}

.close-button:hover {
  color: #666;
}

.upload-body {
  padding: 20px;
}

.file-input {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  opacity: 0;
  cursor: pointer;
}

.upload-content {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 32px 20px;
  border: 1px dashed #d9d9d9;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s;
  background: #fafafa;
}

.upload-content:hover {
  border-color: #409EFF;
  background: #f5f7fa;
}

.upload-text {
  margin-top: 16px;
  text-align: center;
}

.upload-title {
  font-size: 16px;
  color: #333;
  margin-bottom: 8px;
  display: block;
}

.upload-tip {
  margin: 8px 0;
  font-size: 13px;
  color: #666;
}

.upload-plus {
  color: #409EFF;
  font-size: 13px;
  margin-top: 8px;
}
</style>
