<script setup lang="ts">
import { ref } from 'vue';
import { ElRow, ElCol } from 'element-plus';
</script>

<template>
  <div class="user-section">
    <ElRow :gutter="20">
      <ElCol :span="8" class="left-section">
        <!-- Left section content -->
      </ElCol>
      <ElCol :span="16" class="right-section">
        <ElRow :gutter="20">
          <ElCol :span="12" class="top-right-section">
            <!-- Top right section content -->
          </ElCol>
          <ElCol :span="12" class="bottom-right-section">
            <!-- Bottom right section content -->
          </ElCol>
        </ElRow>
      </ElCol>
    </ElRow>
  </div>
</template>

<style scoped>
.user-section {
  display: flex;
  padding: 0 12.5%;
}

.left-section {
  flex: 1;
  /* Add styles for the left section */
}

.right-section {
  flex: 2;
  display: flex;
  flex-direction: column;
}

.top-right-section {
  flex: 1;
  /* Add styles for the top right section */
}

.bottom-right-section {
  flex: 1;
  /* Add styles for the bottom right section */
}
</style>