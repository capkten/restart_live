<script setup lang="ts">
import UserStatusSection from './user/UserStatusSection.vue'
import StatusSection from './status/StatusSection.vue'
import ReviewSection from './review/ReviewSection.vue'
</script>

<template>
  <div class="user-section">
    <div class="grid-container">
      <div class="left-section">
        <UserStatusSection />
      </div>
      <div class="right-top">
        <StatusSection />
      </div>
      <div class="right-bottom">
        <ReviewSection />
      </div>
    </div>
  </div>
</template>

<style scoped>
.user-section {
  padding: 0 12.5%;
}

.grid-container {
  display: grid;
  grid-template-columns: 1fr 2fr;  /* 左边占1份，右边占2份 (1:2 比例) */
  grid-template-rows: auto 1fr;    /* 第一行自适应内容高度，第二行占剩余空间 */
  gap: 20px;                       /* 组件之间的间距 */
}

.left-section {
  grid-row: 1 / 3;                /* 左边部分跨越两行 */
  background-color: #f5f5f5;      /* 临时背景色，方便查看布局 */
  border-radius: 8px;
}

.right-top {
  background-color: #f0f0f0;      /* 临时背景色，方便查看布局 */
  border-radius: 8px;
  min-height: 0;                  /* 允许内容自然撑开 */
}

.right-bottom {
  background-color: #f0f0f0;      /* 临时背景色，方便查看布局 */
  border-radius: 8px;
}
</style>
