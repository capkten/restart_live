<script setup lang="ts">
import { useI18n } from 'vue-i18n'

const { t } = useI18n()
</script>

<template>
  <div class="task-section">
    <div class="section-header">
      <div class="icon-title">
        <span class="icon">👤</span>
        <span class="title">{{ t('userSection.person') }}</span>
      </div>
      <div class="gallery-view">
        <span class="icon">🖼️</span>
        Gallery view
      </div>
    </div>
    <div class="content">
      <!-- Task content will be added here -->
    </div>
  </div>
</template>

<style scoped>
.task-section {
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

.gallery-view {
  display: flex;
  align-items: center;
  gap: 4px;
  font-size: 14px;
  color: #666;
}

.icon {
  font-size: 20px;
}
</style>
