<script setup lang="ts">
import { ref } from 'vue'
import { useI18n } from 'vue-i18n'

const { t } = useI18n()

const tabs = [
  { name: '日记', count: 1 },
  { name: '复盘', count: 0 },
  { name: '灵感', count: 1 },
  { name: '隐藏群组', count: 0 }
]

const activeTab = ref(0)
</script>

<template>
  <div class="review-section">
    <div class="section-header">
      <div class="icon-title">
        <span class="icon">📝</span>
        <span class="title">复盘记录</span>
      </div>
      <div class="board-view">
        <span class="icon">📋</span>
        看板
      </div>
    </div>
    <div class="tabs">
      <div
        v-for="(tab, index) in tabs"
        :key="index"
        class="tab"
        :class="{ active: activeTab === index }"
        @click="activeTab = index"
      >
        {{ tab.name }}
        <span class="count">{{ tab.count }}</span>
      </div>
      <div class="new-page">
        <span class="plus">+</span>
        新页面
      </div>
    </div>
    <div class="content">
      <!-- Content will be added based on active tab -->
    </div>
  </div>
</template>

<style scoped>
.review-section {
  background: #fff;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.icon-title {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 18px;
  font-weight: bold;
}

.board-view {
  display: flex;
  align-items: center;
  gap: 4px;
  font-size: 14px;
  color: #666;
}

.icon {
  font-size: 20px;
}

.tabs {
  display: flex;
  gap: 16px;
  border-bottom: 1px solid #eee;
  padding-bottom: 12px;
  margin-bottom: 20px;
  overflow-x: auto;
}

.tab {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 8px 16px;
  cursor: pointer;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.tab:hover {
  background: #f5f5f5;
}

.tab.active {
  background: #e6f7ff;
  color: #1890ff;
}

.count {
  font-size: 12px;
  background: #f0f0f0;
  padding: 2px 6px;
  border-radius: 10px;
}

.new-page {
  display: flex;
  align-items: center;
  gap: 4px;
  padding: 8px 16px;
  cursor: pointer;
  color: #666;
}

.plus {
  font-size: 16px;
  font-weight: bold;
}

.content {
  min-height: 200px;
}
</style>
