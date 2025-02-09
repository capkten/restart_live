<script setup lang="ts">
import UserStatusSection from './user/UserStatusSection.vue'
import StatusSection from './status/StatusSection.vue'
import ReviewSection from './review/ReviewSection.vue'
import DailyTaskSection from './daily/DailyTaskSection.vue'
</script>

<template>
  <div class="user-section">
    <div class="grid-container">
      <div class="left-side">
        <UserStatusSection />
      </div>
      <div class="right-top">
        <StatusSection />
      </div>
      <div class="right-bottom">
        <ReviewSection />
      </div>
    </div>
    <div class="daily-task-wrapper">
    <DailyTaskSection />
  </div>
  </div>

</template>

<style scoped>
.user-section {
  display: flex;
  flex-direction: column;
  gap: 20px;
  max-width: 1500px; /* 增加最大宽度 */
  margin: 0 auto;
  padding: 0 20px; /* 减小两侧内边距 */
}

.grid-container {
  display: grid;
  grid-template-columns: 380px 1fr; /* 左侧宽度从 320px 增加到 380px */
  grid-template-rows: auto 1fr;
  gap: 20px;
  grid-template-areas:
    "left-side right-top"
    "left-side right-bottom";
}

.left-side {
  grid-area: left-side;
  background-color: #f0f0f0;
  /* 临时背景色，方便查看布局 */
  border-radius: 8px;
}

.right-top {
  grid-area: right-top;
  background-color: #f0f0f0;
  /* 临时背景色，方便查看布局 */
  border-radius: 8px;
}

.right-bottom {
  grid-area: right-bottom;
  background-color: #f0f0f0;
  /* 临时背景色，方便查看布局 */
  border-radius: 8px;
}

.daily-task-wrapper {
  width: 100%;
  height: 600px;
  margin-top: 20px;
}
</style>
