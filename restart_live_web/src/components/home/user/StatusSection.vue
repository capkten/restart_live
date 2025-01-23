<script setup lang="ts">
import { useI18n } from 'vue-i18n'

const { t } = useI18n()

const currentStatus = [
  '拖延症',
  '情绪内耗',
  '习惯性熬夜',
  '控制不了食欲',
  '专业技术停滞'
]

const goals = [
  '克服拖延症',
  '保持情绪稳定',
  '养成有序的每习惯',
  '拒绝熬夜',
  '健康饮食·坚持运动',
  '提升学识'
]
</script>

<template>
  <div class="status-section">
    <div class="section-container">
      <div class="current-status">
        <h3>当前现状</h3>
        <ul>
          <li v-for="(status, index) in currentStatus" :key="index">
            {{ status }}
          </li>
        </ul>
      </div>
      <div class="goals">
        <h3>终极目标</h3>
        <ul>
          <li v-for="(goal, index) in goals" :key="index">
            <input type="checkbox" :id="'goal-' + index">
            <label :for="'goal-' + index">{{ goal }}</label>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<style scoped>
.status-section {
  background: #fff;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.section-container {
  display: flex;
  gap: 40px;
}

.current-status, .goals {
  flex: 1;
}

h3 {
  font-size: 16px;
  margin-bottom: 16px;
  padding: 8px;
  background: #f0f8ff;
  border-radius: 4px;
}

ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

li {
  margin-bottom: 12px;
  font-size: 14px;
  display: flex;
  align-items: center;
  gap: 8px;
}

.goals li {
  display: flex;
  align-items: center;
}

input[type="checkbox"] {
  margin: 0;
}

label {
  cursor: pointer;
}

@media (max-width: 768px) {
  .section-container {
    flex-direction: column;
    gap: 20px;
  }
}
</style>
