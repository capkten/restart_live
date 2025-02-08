<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useI18n } from 'vue-i18n'
import axios from 'axios'

const { t } = useI18n()

interface ReviewRecord {
  id: number
  userId: number
  content: string
  createTime: string
  updateTime: string
  version: number
}

interface ReviewType {
  id: number
  userId: number
  name: string
  sort: number
  isDelete: boolean
  createTime: string
  updateTime: string
  version: number
  reviewRecordList: ReviewRecord[]
}

const reviewTypes = ref<ReviewType[]>([])
const activeTab = ref(0)
const loading = ref(false)

const fetchReviewRecords = async () => {
  try {
    loading.value = true
    const response = await axios.get('/reviewRecord/all')
    if (response.data.code === 200) {
      reviewTypes.value = response.data.data
    }
  } catch (error) {
    console.error('Failed to fetch review records:', error)
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchReviewRecords()
})

const formatDate = (dateString: string) => {
  return new Date(dateString).toLocaleDateString()
}
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
        v-for="(type, index) in reviewTypes"
        :key="type.id"
        class="tab"
        :class="{ active: activeTab === index }"
        @click="activeTab = index"
      >
        {{ type.name }}
        <span class="count">{{ type.reviewRecordList.length }}</span>
      </div>
      <div class="new-page">
        <span class="plus">+</span>
        新页面
      </div>
    </div>
    <div class="content">
      <div v-if="loading" class="loading">
        加载中...
      </div>
      <div v-else-if="reviewTypes.length === 0" class="empty">
        暂无记录
      </div>
      <div v-else class="records">
        <div
          v-for="record in reviewTypes[activeTab]?.reviewRecordList"
          :key="record.id"
          class="record-item"
        >
          <div class="record-content">{{ record.content }}</div>
          <div class="record-time">{{ formatDate(record.createTime) }}</div>
        </div>
      </div>
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

.loading, .empty {
  text-align: center;
  padding: 40px;
  color: #999;
}

.records {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.record-item {
  padding: 16px;
  background: #f8f8f8;
  border-radius: 8px;
  transition: all 0.3s;
}

.record-item:hover {
  background: #f0f0f0;
  transform: translateY(-2px);
}

.record-content {
  margin-bottom: 8px;
  line-height: 1.5;
}

.record-time {
  font-size: 12px;
  color: #999;
}
</style>
