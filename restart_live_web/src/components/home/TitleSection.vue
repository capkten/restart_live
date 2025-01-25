<script setup lang="ts">
import { useI18n } from 'vue-i18n'
import { useUserStore } from '@/stores/user'
import { computed } from 'vue'

const { t } = useI18n()
const userStore = useUserStore()

const title = computed(() => t('titleSection.title'))
const username = computed(() => userStore.userInfo?.username || '')
</script>

<template>
  <div class="title-section">
    <h1 class="title">
      <span>{{ title }}</span>@<span>{{ username }}</span>
    </h1>
  </div>
</template>

<style scoped>
.title-section {
  padding: 40px 0;
  text-align: left;
  padding-left: 12.5%; /* 1/8 = 12.5% */
}

.title {
  font-size: 42px;
  font-weight: bold;
  color: #333;
  margin: 0;
  padding: 0;
  letter-spacing: 2px;
  display: inline-block;
}

@media (max-width: 768px) {
  .title {
    font-size: 32px;
  }
  .title-section {
    padding-left: 8%; /* 稍微减少移动端的左边距 */
  }
}
</style>
