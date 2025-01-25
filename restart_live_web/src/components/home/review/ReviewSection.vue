<script setup lang="ts">
import { ref } from 'vue'
import { Plus } from '@element-plus/icons-vue'

interface ReviewItem {
  date: string
  count: number
  content?: string
}

interface ReviewData {
  daily: ReviewItem[]
  review: ReviewItem[]
  feeling: ReviewItem[]
}

const reviewData = ref<ReviewData>({
  daily: [{ date: '2024年10月18日', count: 1 }],
  review: [{ date: '', count: 0 }],
  feeling: [{ date: '2024年10月18日', count: 1, content: '代码生成器开发' }]
})
</script>

<template>
  <div class="review-section">
    <div class="review-header">
      <div class="header-icon">📝</div>
      <div class="header-title">复盘记录</div>
      <div class="header-divider"></div>
    </div>
    <div class="review-content">
      <div class="cards-container">
        <div class="review-card" :class="{ 'pink-theme': true }">
          <div class="card-header">
            日记
            <el-tag size="small" type="danger" class="count-tag">{{ reviewData.daily[0].count }}</el-tag>
          </div>
          <div class="card-content">
            <div class="review-item" v-for="item in reviewData.daily" :key="item.date">
              <div class="bullet">•</div>
              <span class="date">@{{ item.date }}</span>
              <el-tag size="small" class="count">{{ item.count }}</el-tag>
            </div>
            <div class="add-button">
              <el-button text type="primary">+ 新页面</el-button>
            </div>
          </div>
        </div>

        <div class="review-card" :class="{ 'blue-theme': true }">
          <div class="card-header">
            复盘
            <el-tag size="small" type="info" class="count-tag">{{ reviewData.review[0].count }}</el-tag>
          </div>
          <div class="card-content">
            <div class="add-button">
              <el-button text type="primary">+ 新页面</el-button>
            </div>
          </div>
        </div>

        <div class="review-card" :class="{ 'yellow-theme': true }">
          <div class="card-header">
            灵感
            <el-tag size="small" type="warning" class="count-tag">{{ reviewData.feeling[0].count }}</el-tag>
          </div>
          <div class="card-content">
            <div class="review-item" v-for="item in reviewData.feeling" :key="item.date">
              <span class="emoji">😊</span>
              <span class="date">@{{ item.date }}</span>
              <div class="item-content">{{ item.content }}</div>
            </div>
            <div class="add-button">
              <el-button text type="primary">+ 新页面</el-button>
            </div>
          </div>
        </div>
      </div>

      <div class="add-category">
        <el-button class="add-btn" text>
          <el-icon class="add-icon"><Plus /></el-icon>
        </el-button>
        <div class="add-text">隐藏群组</div>
        <div class="add-avatar">
          <el-avatar size="small">无分身</el-avatar>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.review-section {
  height: 100%;
  background-color: white;
  border-radius: 8px;
}

.review-header {
  display: flex;
  align-items: center;
  padding: 16px 20px;
  background-color: #EBF5FF;
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;
}

.header-icon {
  font-size: 20px;
  margin-right: 8px;
}

.header-title {
  font-size: 16px;
  font-weight: 500;
  color: #333;
}

.header-divider {
  flex: 1;
  height: 1px;
  background-color: #E4E7ED;
  margin-left: 16px;
}

.review-content {
  padding: 20px;
  display: flex;
  gap: 20px;
  overflow: hidden;
}

.cards-container {
  display: flex;
  gap: 20px;
  overflow-x: auto;
  padding-bottom: 12px;
  flex: 1;
}

.cards-container::-webkit-scrollbar {
  height: 8px;
}

.cards-container::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 4px;
}

.cards-container::-webkit-scrollbar-thumb {
  background: #dcdfe6;
  border-radius: 4px;
}

.cards-container::-webkit-scrollbar-thumb:hover {
  background: #c0c4cc;
}

.review-card {
  flex: 0 0 340px;
  background-color: white;
  border-radius: 8px;
  padding: 16px;
  min-height: 240px;
}

.pink-theme {
  background-color: #FFF0F5;
}

.blue-theme {
  background-color: #F0F8FF;
}

.yellow-theme {
  background-color: #FFFAF0;
}

.card-header {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 14px;
  font-weight: 500;
  margin-bottom: 16px;
  white-space: nowrap;
}

.count-tag {
  font-size: 12px;
}

.card-content {
  height: calc(100% - 40px);
  display: flex;
  flex-direction: column;
}

.review-item {
  display: flex;
  align-items: center;
  gap: 12px;
  color: #666;
  font-size: 14px;
  white-space: nowrap;
  overflow: hidden;
}

.bullet {
  color: #409EFF;
}

.date {
  color: #666;
  flex-shrink: 0;
}

.item-content {
  margin-top: 4px;
  padding-left: 24px;
  color: #333;
  white-space: normal;
  word-break: break-all;
  min-width: 0;
}

.add-button {
  margin-top: auto;
}

.add-button :deep(.el-button) {
  color: #409EFF;
  font-size: 14px;
}

.add-category {
  flex: 0 0 80px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  gap: 8px;
  padding-top: 16px;
}

.add-btn {
  width: 32px;
  height: 32px;
  padding: 0;
  border: 1px dashed #dcdfe6;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.add-icon {
  font-size: 16px;
  color: #909399;
}

.add-text {
  font-size: 12px;
  color: #909399;
  text-align: center;
}

.add-avatar {
  margin-top: auto;
}
</style>
