<script setup lang="ts">
import { ref, onMounted, nextTick } from 'vue'
import { ElMessage } from 'element-plus'
import request from '@/utils/request'

interface StatusItem {
  id: number
  userId: number
  content: string
  createTime: number[]
  updateTime: number[]
  isDelete: boolean
  version: number
  index: number | null
}

interface FinalAimItem {
  id: number
  userId: number
  content: string
  createTime: string
  updateTime: string
  isDelete: boolean
  isFinished: boolean
  version: number
  index: number
}

interface ApiResponse {
  code: number
  msg: string
  data: StatusItem[]
}

interface FinalAimResponse {
  code: number
  message: string
  data: FinalAimItem[]
}

interface Status {
  current: StatusItem[]
  goals: FinalAimItem[]
}

interface SaveResponse {
  code: number
  msg: string
  data: null
}

interface SaveFinalAimResponse {
  code: number
  message: string
  data: null
}

const status = ref<Status>({
  current: [],
  goals: []
})

// 获取状态列表
const fetchStatusList = async () => {
  try {
    const response = await request<ApiResponse>({
      url: '/status/all',
      method: 'GET'
    })

    console.log('API Response data:', response.data) // 添加日志
    const res = response.data
    if (res.code === 200) {
      // 处理 index 为 null 的情况并排序
      const sortedData = res.data
        .filter(item => !item.isDelete)
        .map(item => ({
          ...item,
          // 确保 createTime 和 updateTime 是数组
          createTime: Array.isArray(item.createTime) ? item.createTime : [],
          updateTime: Array.isArray(item.updateTime) ? item.updateTime : [],
          index: item.index ?? 0  // 如果 index 为 null 或 undefined，设置为 0
        }))
        .sort((a, b) => (a.index || 0) - (b.index || 0))

      console.log('Sorted Data:', sortedData) // 添加日志
      status.value.current = sortedData
      console.log('Status Current:', status.value.current) // 添加日志
    } else {
      ElMessage.error(res.msg)
    }
  } catch (error) {
    console.error('Response Error:', error) // 添加更详细的错误日志
    ElMessage.error('获取状态列表失败，请稍后重试')
  }
}

// 获取终极目标列表
const fetchFinalAims = async () => {
  try {
    const response = await request<FinalAimResponse>({
      url: '/finalAim/all',
      method: 'GET'
    })

    console.log('Final Aims Response:', response.data)
    const res = response.data
    if (res.code === 200) {
      // 处理并排序数据
      const sortedData = res.data
        .filter(item => !item.isDelete)
        .sort((a, b) => (a.index || 0) - (b.index || 0))

      console.log('Sorted Final Aims:', sortedData)
      status.value.goals = sortedData
    } else {
      ElMessage.error(res.message)
    }
  } catch (error) {
    console.error('Final Aims Error:', error)
    ElMessage.error('获取终极目标列表失败，请稍后重试')
  }
}

// 组件挂载时获取状态列表和终极目标列表
onMounted(() => {
  fetchStatusList()
  fetchFinalAims()
})

const handleKeyDown = (event: KeyboardEvent, type: 'current' | 'goals', index: number) => {
  const target = event.target as HTMLElement
  const text = target.textContent || ''

  if (event.key === 'Enter') {
    event.preventDefault()
    if (type === 'current') {
      status.value.current.splice(index + 1, 0, {
        id: 0,
        userId: 0,
        content: '',
        createTime: [],
        updateTime: [],
        isDelete: false,
        version: 1,
        index: null
      })
    } else {
      status.value.goals.splice(index + 1, 0, {
        id: 0,
        userId: 0,
        content: '',
        createTime: new Date().toISOString(),
        updateTime: new Date().toISOString(),
        isDelete: false,
        isFinished: false,
        version: 1,
        index: index + 1
      })
    }

    // 修改为使用 nextTick 和更精确的选择器
    nextTick(() => {
      const container = type === 'current' ? '.status-list' : '.goals-list'
      const items = document.querySelectorAll(`${container} [contenteditable]`)
      const nextElement = items[index + 1] as HTMLElement
      if (nextElement) {
        nextElement.focus()
      }
    })
  } else if (event.key === 'Backspace' && text === '') {
    event.preventDefault()
    if (type === 'current') {
      if (status.value.current.length > 1) {
        status.value.current.splice(index, 1)
      }
    } else {
      if (status.value.goals.length > 1) {
        status.value.goals.splice(index, 1)
      }
    }

    setTimeout(() => {
      const prevElement = target.parentElement?.parentElement?.previousElementSibling?.querySelector('[contenteditable]')
      if (prevElement) {
        ;(prevElement as HTMLElement).focus()
      }
    }, 0)
  }
}

const updateContent = (type: 'current' | 'goals', index: number, event: Event) => {
  const target = event.target as HTMLElement
  const text = target.textContent || ''
  if (type === 'current') {
    status.value.current[index].content = text
  } else {
    status.value.goals[index].content = text
  }
}

const toggleGoal = (index: number) => {
  status.value.goals[index].isFinished = !status.value.goals[index].isFinished
}

// 添加第一行数据
const handleAreaDblClick = (type: 'current' | 'goals') => {
  if (type === 'current' && status.value.current.length === 0) {
    status.value.current.push({
      id: 0,
      userId: 0,
      content: '',
      createTime: [],
      updateTime: [],
      isDelete: false,
      version: 1,
      index: null
    })
    // 等待 DOM 更新后聚焦到新创建的输入框
    setTimeout(() => {
      const element = document.querySelector('.current-status [contenteditable]')
      if (element) {
        ;(element as HTMLElement).focus()
      }
    }, 0)
  } else if (type === 'goals' && status.value.goals.length === 0) {
    status.value.goals.push({
      id: 0,
      userId: 0,
      content: '',
      createTime: new Date().toISOString(),
      updateTime: new Date().toISOString(),
      isDelete: false,
      isFinished: false,
      version: 1,
      index: 0
    })
    setTimeout(() => {
      const element = document.querySelector('.goal-status [contenteditable]')
      if (element) {
        ;(element as HTMLElement).focus()
      }
    }, 0)
  }
}

// 保存状态列表
const saveStatusList = async () => {
  try {
    const dataToSave = status.value.current.map((item, index) => ({
      ...item,
      index: index
    }))

    const response = await request<SaveResponse>({
      url: '/status/change',
      method: 'POST',
      data: dataToSave
    })
    console.log('save data response', response)
    if (response.data.code !== 200) {
      ElMessage.error(response.data.msg)
    }
  } catch (error) {
    ElMessage.error('保存失败，请稍后重试')
    console.error('保存状态列表失败:', error)
  }
}

// 保存终极目标列表
const saveFinalAims = async () => {
  try {
    const dataToSave = status.value.goals.map((item, index) => ({
      ...item,
      index: index
    }))

    const response = await request<SaveFinalAimResponse>({
      url: '/finalAim/change',
      method: 'POST',
      data: dataToSave
    })
    console.log('save final aims response', response)
    if (response.data.code !== 200) {
      ElMessage.error(response.data.message)
    }
  } catch (error) {
    ElMessage.error('保存失败，请稍后重试')
    console.error('保存终极目标列表失败:', error)
  }
}

// 修改组件失焦处理，使用防抖来避免频繁保存
let saveTimeout: ReturnType<typeof setTimeout> | null = null
const handleComponentBlur = (event: FocusEvent) => {
  // 检查是否真的失去焦点到组件外
  const relatedTarget = event.relatedTarget as HTMLElement | null
  const currentTarget = event.currentTarget as HTMLElement
  if (relatedTarget && currentTarget.contains(relatedTarget)) {
    return // 如果焦点仍在组件内，不执行保存
  }

  // 清除之前的定时器
  if (saveTimeout) {
    clearTimeout(saveTimeout)
  }

  // 设置新的定时器
  saveTimeout = setTimeout(() => {
    if (status.value.current.length > 0) {
      saveStatusList()
    }
    if (status.value.goals.length > 0) {
      saveFinalAims()
    }
  }, 300) // 300ms 的延迟
}
</script>

<template>
  <div
    class="status-container"
    @blur="handleComponentBlur"
    @focusout="handleComponentBlur"
    tabindex="-1"
  >
    <el-card class="status-card current-status">
      <template #header>
        <div class="card-header">
          <span class="header-title">当前现状</span>
        </div>
      </template>
      <ul
        class="status-list"
        @dblclick="handleAreaDblClick('current')"
      >
        <li
          v-for="(item, index) in status.current"
          :key="item.id || index"
          class="editable-item"
        >
          <span
            contenteditable="true"
            @keydown="(e) => handleKeyDown(e, 'current', index)"
            @input="(e) => updateContent('current', index, e)"
            @blur="(e) => updateContent('current', index, e)"
            v-text="item.content"
          ></span>
        </li>
        <li v-if="status.current.length === 0" class="empty-tip">
          双击添加内容
        </li>
      </ul>
    </el-card>

    <el-card class="status-card goal-status">
      <template #header>
        <div class="card-header">
          <span class="header-title">终极目标</span>
        </div>
      </template>
      <div
        class="goals-list"
        @dblclick="handleAreaDblClick('goals')"
      >
        <div
          v-for="(goal, index) in status.goals"
          :key="index"
          class="goal-item"
        >
          <div class="goal-content">
            <input
              type="checkbox"
              :checked="goal.isFinished"
              @change="() => toggleGoal(index)"
              class="goal-checkbox"
            />
            <span
              contenteditable="true"
              :class="{ completed: goal.isFinished }"
              @keydown="(e) => handleKeyDown(e, 'goals', index)"
              @input="(e) => updateContent('goals', index, e)"
              @blur="(e) => updateContent('goals', index, e)"
            >{{ goal.content }}</span>
          </div>
        </div>
        <div v-if="status.goals.length === 0" class="empty-tip">
          双击添加内容
        </div>
      </div>
    </el-card>
  </div>
</template>

<style scoped>
.status-container {
  display: flex;
  gap: 20px;
  outline: none;
}

.status-card {
  flex: 1;
}

.card-header {
  display: flex;
  align-items: center;
}

.header-title {
  font-size: 16px;
  font-weight: 500;
}

.status-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.status-list li, .goal-item {
  margin-bottom: 12px;
  position: relative;
}

.status-list li {
  padding-left: 20px;
}

.status-list li::before {
  content: "•";
  position: absolute;
  left: 0;
  top: 2px;
  color: #409EFF;
}

.goals-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.goal-item :deep(.el-checkbox) {
  --el-checkbox-font-size: 14px;
  --el-checkbox-text-color: #666;
}

:deep(.el-card__header) {
  padding: 12px 20px;
  background-color: var(--el-fill-color-light);
}

/* 当前现状卡片样式 */
.current-status :deep(.el-card__header) {
  background-color: rgba(64, 158, 255, 0.1); /* 淡蓝色背景 */
}

.current-status .header-title {
  color: #409EFF; /* 蓝色标题 */
}

.current-status .status-list li::before {
  color: #409EFF; /* 蓝色圆点 */
}

.current-status .status-list li {
  color: #409EFF; /* 蓝色文字 */
}

/* 终极目标卡片样式 */
.goal-status :deep(.el-card__header) {
  background-color: rgba(255, 192, 203, 0.1); /* 淡粉色背景 */
}

.goal-status .header-title {
  color: #FF69B4; /* 粉色标题 */
}

.goal-status :deep(.el-checkbox) {
  --el-checkbox-text-color: #FF69B4;
  --el-checkbox-border-color: #FF69B4;
  --el-checkbox-checked-bg-color: #FF69B4;
  --el-checkbox-checked-border-color: #FF69B4;
  height: 24px; /* 设置固定高度 */
  margin: 0; /* 移除默认边距 */
}

.goal-status :deep(.el-checkbox__input.is-checked .el-checkbox__inner) {
  background-color: #FF69B4;
  border-color: #FF69B4;
}

.goal-status :deep(.el-checkbox__label) {
  display: none;
}

.goal-status .completed {
  text-decoration: line-through;
  opacity: 0.7;
}

.editable-item {
  min-height: 24px;
  cursor: text;
}

.editable-item span[contenteditable] {
  display: block;
  outline: none;
  min-height: 24px;
  padding: 2px 0;
}

.editable-item span[contenteditable]:empty::before {
  content: '点击输入...';
  color: #999;
}

/* 修改当前现状的样式 */
.current-status .editable-item span[contenteditable] {
  color: #409EFF;
}

/* 修改终极目标的样式 */
.goal-status .editable-item span[contenteditable] {
  color: #FF69B4;
}

/* 移除复选框相关样式，因为不再使用 */
.goal-status .goals-list {
  padding: 0;
}

.goal-status .goal-item {
  position: relative;
  margin-bottom: 12px;
}

[contenteditable] {
  outline: none;
  min-height: 24px;
  padding: 2px 0;
}

[contenteditable]:empty::before {
  content: '点击输入...';
  color: #999;
}

/* 文本颜色 */
.current-status [contenteditable] {
  color: #409EFF;
}

.goal-status [contenteditable] {
  color: #FF69B4;
}

.goal-content {
  display: flex;
  align-items: center; /* 改为 center 以对齐复选框和文字 */
  gap: 8px;
}

.goal-content span[contenteditable] {
  flex: 1;
  line-height: 24px; /* 设置行高与复选框一致 */
  min-height: 24px;
  padding: 0; /* 移除内边距 */
}

.empty-tip {
  color: #909399;
  font-size: 14px;
  text-align: center;
  padding: 20px 0;
  cursor: pointer;
}

.status-list, .goals-list {
  min-height: 100px; /* 确保空状态时有足够的点击区域 */
  cursor: pointer;
}

.goal-checkbox {
  width: 16px;
  height: 16px;
  margin-right: 8px;
  cursor: pointer;
}
</style>
