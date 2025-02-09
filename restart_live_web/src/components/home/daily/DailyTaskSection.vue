<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { ArrowLeft, ArrowRight } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import request from '@/utils/request'

interface DailyTask {
  id: number
  userId: number
  name: string
  time: string // 格式 "HH:mm"
  isCompleted: boolean
  date: string // 格式 "YYYY-MM-DD"
}

const currentDate = ref(new Date())
const tasks = ref<DailyTask[]>([])
const loading = ref(false)

// 计算当前月份的第一天
const firstDayOfMonth = computed(() => {
  const date = new Date(currentDate.value)
  date.setDate(1)
  return date
})

// 计算当前月份的天数
const daysInMonth = computed(() => {
  const date = new Date(currentDate.value)
  return new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate()
})

// 计算日历网格需要的所有日期
const calendarDays = computed(() => {
  const days: Array<{ date: Date; tasks: DailyTask[] }> = []
  const firstDay = firstDayOfMonth.value
  const startingDay = firstDay.getDay() // 0 = Sunday

  // 添加上个月的日期
  const prevMonthDays = startingDay === 0 ? 6 : startingDay - 1
  for (let i = prevMonthDays; i > 0; i--) {
    const date = new Date(firstDay)
    date.setDate(firstDay.getDate() - i)
    days.push({ date, tasks: getTasksForDate(date) })
  }

  // 添加当前月的日期
  for (let i = 1; i <= daysInMonth.value; i++) {
    const date = new Date(firstDay)
    date.setDate(i)
    days.push({ date, tasks: getTasksForDate(date) })
  }

  // 添加下个月的日期以填充最后一行
  const remainingDays = 42 - days.length // 6行7列 = 42
  for (let i = 1; i <= remainingDays; i++) {
    const date = new Date(firstDay)
    date.setMonth(date.getMonth() + 1)
    date.setDate(i)
    days.push({ date, tasks: getTasksForDate(date) })
  }

  return days
})

// 获取指定日期的任务
const getTasksForDate = (date: Date) => {
  const dateStr = formatDate(date)
  return tasks.value.filter(task => task.date === dateStr)
}

// 格式化日期为 YYYY-MM-DD
const formatDate = (date: Date) => {
  return date.toISOString().split('T')[0]
}

// 格式化月份显示
const formatMonth = (date: Date) => {
  return `${date.getFullYear()}年${date.getMonth() + 1}月`
}

// 切换到上个月
const prevMonth = () => {
  const date = new Date(currentDate.value)
  date.setMonth(date.getMonth() - 1)
  currentDate.value = date
  fetchTasks()
}

// 切换到下个月
const nextMonth = () => {
  const date = new Date(currentDate.value)
  date.setMonth(date.getMonth() + 1)
  currentDate.value = date
  fetchTasks()
}

// 获取任务数据
const fetchTasks = async () => {
  try {
    loading.value = true
    const response = await request.get('/dailyTask/list', {
      params: {
        year: currentDate.value.getFullYear(),
        month: currentDate.value.getMonth() + 1
      }
    })
    if (response.data.code === 200) {
      tasks.value = response.data.data
    }
  } catch (error) {
    console.error('Failed to fetch tasks:', error)
    ElMessage.error('获取任务失败')
  } finally {
    loading.value = false
  }
}

// 切换任务完成状态
const toggleTaskStatus = async (task: DailyTask) => {
  try {
    const response = await request.post('/dailyTask/toggleStatus', {
      id: task.id
    })
    if (response.data.code === 200) {
      task.isCompleted = !task.isCompleted
      ElMessage.success(task.isCompleted ? '已完成' : '已取消完成')
    }
  } catch (error) {
    console.error('Failed to toggle task status:', error)
    ElMessage.error('操作失败')
  }
}

onMounted(() => {
  fetchTasks()
})
</script>

<template>
  <div class="daily-task-section">
    <div class="section-header">
      <div class="header-icon">📅</div>
      <div class="header-title">每日任务</div>
      <div class="header-divider"></div>
    </div>

    <div class="calendar-header">
      <div class="month-nav">
        <el-button text :icon="ArrowLeft" @click="prevMonth" />
        <span class="current-month">{{ formatMonth(currentDate) }}</span>
        <el-button text :icon="ArrowRight" @click="nextMonth" />
      </div>
      <div class="weekdays">
        <div class="weekday">一</div>
        <div class="weekday">二</div>
        <div class="weekday">三</div>
        <div class="weekday">四</div>
        <div class="weekday">五</div>
        <div class="weekday weekend">六</div>
        <div class="weekday weekend">日</div>
      </div>
    </div>

    <div v-if="loading" class="loading">
      加载中...
    </div>
    <div v-else class="calendar-grid">
      <div
        v-for="(day, index) in calendarDays"
        :key="index"
        class="day-cell"
        :class="{
          'other-month': day.date.getMonth() !== currentDate.getMonth(),
          'today': formatDate(day.date) === formatDate(new Date())
        }"
      >
        <div class="day-header">
          {{ day.date.getDate() }}
        </div>
        <div class="day-content">
          <div
            v-for="task in day.tasks"
            :key="task.id"
            class="task-item"
            :class="{ completed: task.isCompleted }"
            @click="toggleTaskStatus(task)"
          >
            <span class="task-time">{{ task.time }}</span>
            <span class="task-name">{{ task.name }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.daily-task-section {
  background-color: white;
  border-radius: 8px;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.section-header {
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

.calendar-header {
  padding: 16px;
  border-bottom: 1px solid #E4E7ED;
}

.month-nav {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 16px;
  margin-bottom: 16px;
}

.current-month {
  font-size: 16px;
  font-weight: 500;
  min-width: 100px;
  text-align: center;
}

.weekdays {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  text-align: center;
  color: #606266;
  font-size: 14px;
}

.weekday {
  padding: 8px;
}

.weekend {
  color: #F56C6C;
}

.calendar-grid {
  flex: 1;
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  grid-template-rows: repeat(6, 1fr);
  gap: 1px;
  background-color: #E4E7ED;
  padding: 1px;
}

.day-cell {
  background-color: white;
  padding: 8px;
  min-height: 100px;
  display: flex;
  flex-direction: column;
}

.day-header {
  font-size: 14px;
  color: #606266;
  margin-bottom: 8px;
}

.other-month {
  background-color: #F5F7FA;

  .day-header {
    color: #909399;
  }
}

.today {
  .day-header {
    color: #409EFF;
    font-weight: bold;
  }
}

.day-content {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 4px;
  overflow-y: auto;
}

.task-item {
  display: flex;
  align-items: center;
  gap: 4px;
  padding: 4px;
  border-radius: 4px;
  font-size: 12px;
  cursor: pointer;
  transition: background-color 0.3s;

  &:hover {
    background-color: #F5F7FA;
  }

  &.completed {
    color: #909399;
    text-decoration: line-through;
  }
}

.task-time {
  color: #409EFF;
  flex-shrink: 0;
}

.task-name {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.loading {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #909399;
}
</style>
