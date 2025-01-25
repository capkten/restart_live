<script setup lang="ts">
import { ref } from 'vue'

interface GoalItem {
  text: string
  completed: boolean
}

interface Status {
  current: string[]
  goals: GoalItem[]
}

const status = ref<Status>({
  current: [
    '拖延症',
    '情绪内耗',
    '习惯性熬夜',
    '控制不了食欲',
    '专业技术停滞'
  ],
  goals: [
    { text: '克服拖延症', completed: false },
    { text: '保持情绪稳定', completed: false },
    { text: '养成看书的好习惯', completed: false },
    { text: '拒绝熬夜', completed: false },
    { text: '健康饮食·坚持运动', completed: false },
    { text: '提升学识', completed: false }
  ]
})

const handleKeyDown = (event: KeyboardEvent, type: 'current' | 'goals', index: number) => {
  const target = event.target as HTMLElement
  const text = target.textContent || ''

  if (event.key === 'Enter') {
    event.preventDefault()
    if (type === 'current') {
      status.value.current.splice(index + 1, 0, '')
    } else {
      status.value.goals.splice(index + 1, 0, { text: '', completed: false })
    }

    setTimeout(() => {
      const nextElement = target.parentElement?.parentElement?.nextElementSibling?.querySelector('[contenteditable]')
      if (nextElement) {
        ;(nextElement as HTMLElement).focus()
      }
    }, 0)
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
    status.value.current[index] = text
  } else {
    status.value.goals[index].text = text
  }
}

const toggleGoal = (index: number) => {
  status.value.goals[index].completed = !status.value.goals[index].completed
}
</script>

<template>
  <div class="status-container">
    <el-card class="status-card current-status">
      <template #header>
        <div class="card-header">
          <span class="header-title">当前现状</span>
        </div>
      </template>
      <ul class="status-list">
        <li
          v-for="(item, index) in status.current"
          :key="index"
          class="editable-item"
        >
          <span
            contenteditable="true"
            @keydown="(e) => handleKeyDown(e, 'current', index)"
            @input="(e) => updateContent('current', index, e)"
            @blur="(e) => updateContent('current', index, e)"
          >{{ item }}</span>
        </li>
      </ul>
    </el-card>

    <el-card class="status-card goal-status">
      <template #header>
        <div class="card-header">
          <span class="header-title">终极目标</span>
        </div>
      </template>
      <div class="goals-list">
        <div
          v-for="(goal, index) in status.goals"
          :key="index"
          class="goal-item"
        >
          <div class="goal-content">
            <el-checkbox
              v-model="goal.completed"
              :class="{ 'is-checked': goal.completed }"
            />
            <span
              contenteditable="true"
              :class="{ completed: goal.completed }"
              @keydown="(e) => handleKeyDown(e, 'goals', index)"
              @input="(e) => updateContent('goals', index, e)"
              @blur="(e) => updateContent('goals', index, e)"
            >{{ goal.text }}</span>
          </div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<style scoped>
.status-container {
  display: flex;
  gap: 20px;
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
</style>
