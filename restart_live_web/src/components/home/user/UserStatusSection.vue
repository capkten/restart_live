<script setup lang="ts">
import { useI18n } from 'vue-i18n'
import { ElCard, ElProgress } from 'element-plus'

const { t } = useI18n()

interface UserStatus {
  name: string
  age: number
  daysFromBirth: number
  tags: string[]
  taskCompletion: number
  totalTasks: number
  starCount: number
  friendCount: number
}

// 模拟数据，之后可以通过props传入或从store获取
const userStatus: UserStatus = {
  name: 'Capkin',
  age: 22,
  daysFromBirth: 49,
  tags: ['虚弱的胖子', '后端工程师'],
  taskCompletion: 0,
  totalTasks: 1,
  starCount: 110,
  friendCount: 0
}
</script>

<template>
  <el-card class="user-status" :body-style="{ padding: '20px' }">
    <div class="user-basic">
      <h2>{{ userStatus.name }}</h2>
      <p class="age-info">{{ userStatus.age }}岁，距离生日还有<span class="highlight">{{ userStatus.daysFromBirth }}</span>天</p>
    </div>

    <div class="user-tags">
      <el-tag
        v-for="tag in userStatus.tags"
        :key="tag"
        class="tag"
        size="small"
        effect="plain"
      >
        {{ tag }}
      </el-tag>
    </div>

    <el-progress
      :percentage="0"
      :show-text="false"
      class="progress-bar"
    />

    <div class="progress-section">
      <p>
        <span class="emoji">👍</span>
        还没有任务完成，请继续加油吧！
      </p>
      <p>
        还没有<span class="emoji">👋</span>人来，继续加油吧！
      </p>
    </div>

    <div class="stats-section">
      <div class="stat-item">
        <el-tag size="small" effect="plain" class="star-tag">
          ⭐ × {{ userStatus.starCount }}
        </el-tag>
        <span class="stat-label">当前评分</span>
      </div>
      <div class="stat-item">
        <span class="icon">▶</span>
        <span class="stat-text">人生进度完成进度：{{ userStatus.taskCompletion }}/{{ userStatus.totalTasks }}</span>
      </div>
    </div>
  </el-card>
</template>

<style scoped>
.user-status {
  height: 100%;
}

.user-basic h2 {
  margin: 0;
  font-size: 1.2em;
  font-weight: 600;
  margin-bottom: 8px;
}

.age-info {
  margin: 0;
  color: #666;
  font-size: 0.9em;
}

.highlight {
  color: #409EFF;
}

.user-tags {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
  margin: 16px 0;
}

.progress-bar {
  margin: 16px 0;
}

.progress-section {
  color: #666;
  font-size: 0.9em;
  margin: 16px 0;
}

.progress-section p {
  margin: 8px 0;
  display: flex;
  align-items: center;
  gap: 4px;
}

.emoji {
  font-size: 1.2em;
}

.stats-section {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.stat-item {
  display: flex;
  align-items: center;
  gap: 8px;
}

.star-tag {
  background-color: transparent;
  border: none;
  padding: 0;
}

.stat-label {
  color: #666;
  font-size: 0.9em;
}

.icon {
  font-size: 1em;
  color: #409EFF;
}

.stat-text {
  font-size: 0.9em;
  color: #666;
}
</style>
