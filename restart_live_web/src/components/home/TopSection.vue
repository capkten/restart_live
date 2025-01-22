<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import { ElPopover, ElIcon, ElMessage } from 'element-plus'
import 'element-plus/dist/index.css'
import { getUserConfig, saveUserIcon } from '@/api/config'

// 背景图路径
const topImage = ref('')

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
    await saveUserIcon(icon.name)
    console.log('[TopSection] Successfully saved icon')

    currentIcon.value = icon.component
    showIconSelector.value = false
    ElMessage.success('图标保存成功')
  } catch (error) {
    console.error('[TopSection] Failed to save icon:', error)
    if (error instanceof Error) {
      console.error('[TopSection] Error message:', error.message)
      console.error('[TopSection] Error stack:', error.stack)
    }
    ElMessage.error('图标保存失败')
  }
}

// 组件加载时获取用户配置
onMounted(() => {
  loadUserConfig()
})
</script>

<template>
  <div class="image-container">
    <img :src="topImage" alt="顶部图片" class="top-image">
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
            placeholder="搜索图标..."
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
  height: 30%;
  margin: 0;
  padding: 0;
  position: absolute;
  top: 0;
  left: 0;
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
</style>
