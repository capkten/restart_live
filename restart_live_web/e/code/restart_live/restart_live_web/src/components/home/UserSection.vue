<script setup lang="ts">
import { ref } from 'vue';

const leftSectionRef = ref(null);
const rightTopSectionRef = ref(null);
const rightBottomSectionRef = ref(null);
</script>

<template>
  <div class="user-section">
    <div ref="leftSectionRef" class="left-section">
      <!-- Left section content -->
    </div>
    <div ref="rightTopSectionRef" class="right-top-section">
      <!-- Right top section content -->
    </div>
    <div ref="rightBottomSectionRef" class="right-bottom-section">
      <!-- Right bottom section content -->
    </div>
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

.right-top-section {
  flex: 1;
  margin-left: 20px;
  /* Add styles for the right top section */
}

.right-bottom-section {
  flex: 1;
  margin-left: 20px;
  margin-top: 20px;
  /* Add styles for the right bottom section */
}
</style>