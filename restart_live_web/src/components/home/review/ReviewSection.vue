<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useI18n } from 'vue-i18n'
import { Plus, ArrowRight, Edit, Delete } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import request from '@/utils/request'
import { MdEditor } from 'md-editor-v3'
import 'md-editor-v3/lib/style.css'

const { t } = useI18n()

interface ReviewRecord {
  id: number
  userId: number
  rtId: number
  isDelete: boolean
  content: string
  createTime: number[]
  updateTime: number[]
  date: number[]
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

interface ApiResponse {
  code: number
  msg: string
  data: ReviewType[]
}

const reviewTypes = ref<ReviewType[]>([])
const loading = ref(false)
const showNewGroupInput = ref(false)
const newGroupName = ref('')
const showNewPage = ref(false)
const currentTypeId = ref(0)
const newPageContent = ref('')
const newPageDate = ref(new Date().toISOString().split('T')[0])
const editingTypeId = ref<number | null>(null)
const editingTypeName = ref('')
const isEditing = ref(false)
const editingRecord = ref<ReviewRecord | null>(null)

const fetchReviewRecords = async () => {
  try {
    loading.value = true
    const response = await request.get<ApiResponse>('/reviewRecord/all')
    reviewTypes.value = response.data.data.map(type => ({
      ...type,
      reviewRecordList: type.reviewRecordList || []
    })).sort((a, b) => a.sort - b.sort)
  } catch (error) {
    console.error('Failed to fetch review records:', error)
    ElMessage.error('获取记录失败')
  } finally {
    loading.value = false
  }
}

const addNewGroup = async () => {
  if (!newGroupName.value.trim()) {
    ElMessage.warning('请输入分组名称')
    return
  }

  const formData = new FormData()
  formData.append('name', newGroupName.value.trim())
  formData.append('sort', String(reviewTypes.value.length > 0 ? reviewTypes.value.length + 1 : 1))

  try {
    const response = await request.post('/reviewType/add', formData)
    if (response.data.code === 200) {
      ElMessage.success('添加成功')
      fetchReviewRecords() // 重新获取列表
      newGroupName.value = '' // 清空输入
      showNewGroupInput.value = false // 关闭输入框
    } else {
      ElMessage.error(response.data.msg || '添加失败')
    }
  } catch (error) {
    console.error('Failed to add review type:', error)
    ElMessage.error(error instanceof Error ? error.message : '添加失败')
  }
}

const openNewPage = (typeId: number, record?: ReviewRecord) => {
  currentTypeId.value = typeId
  showNewPage.value = true

  if (record) {
    // 编辑模式
    isEditing.value = true
    editingRecord.value = record
    newPageContent.value = record.content
    // 将日期数组转换为字符串格式
    const [year, month, day] = record.date
    newPageDate.value = `${year}-${String(month).padStart(2, '0')}-${String(day).padStart(2, '0')}`
  } else {
    // 新增模式
    isEditing.value = false
    editingRecord.value = null
    newPageContent.value = ''
    newPageDate.value = new Date().toISOString().split('T')[0]
  }
}

const closeNewPage = () => {
  showNewPage.value = false
  newPageContent.value = ''
  isEditing.value = false
  editingRecord.value = null
}

const saveNewPage = async () => {
  if (!newPageContent.value.trim()) {
    ElMessage.warning('请输入内容')
    return
  }

  try {
    if (isEditing.value && editingRecord.value) {
      // 编辑模式
      const response = await request.post('/reviewRecord/change', {
        id: editingRecord.value.id,
        content: newPageContent.value.trim(),
        date: newPageDate.value
      })
      if (response.data.code === 200) {
        ElMessage.success('修改成功')
        fetchReviewRecords()
        closeNewPage()
      }
    } else {
      // 新增模式
      const response = await request.post('/reviewRecord/add', {
        rtId: currentTypeId.value,
        date: newPageDate.value,
        content: newPageContent.value.trim()
      })
      if (response.data.code === 200) {
        ElMessage.success('添加成功')
        fetchReviewRecords()
        closeNewPage()
      }
    }
  } catch (error) {
    console.error('Failed to save review record:', error)
    ElMessage.error(error instanceof Error ? error.message : '保存失败')
  }
}

const handleImageUpload = async (files: Array<File>, callback: (urls: string[]) => void) => {
  try {
    const formData = new FormData()
    formData.append('file', files[0])

    const response = await request.post('/file/upload', formData)
    if (response.data.code === 200) {
      callback([response.data.data.url])
    } else {
      ElMessage.error(response.data.msg || '图片上传失败')
    }
  } catch (error) {
    console.error('Failed to upload image:', error)
    ElMessage.error('图片上传失败')
  }
}

onMounted(() => {
  fetchReviewRecords()
})

const formatDate = (dateString: string) => {
  const date = new Date(dateString)
  return `${date.getFullYear()}年${String(date.getMonth() + 1).padStart(2, '0')}月${String(date.getDate()).padStart(2, '0')}日`
}

const formatArrayDate = (dateArray: number[]) => {
  if (!Array.isArray(dateArray) || dateArray.length < 3) return ''
  const [year, month, day] = dateArray
  return `${year}年${String(month).padStart(2, '0')}月${String(day).padStart(2, '0')}日`
}

const truncateContent = (content: string) => {
  // 获取第一行内容（通过换行符分割取第一行）
  const firstLine = content.split('\n')[0]
  // 如果第一行超过10个字符，截取前10个字符并加上省略号
  if (firstLine.length > 10) {
    return firstLine.substring(0, 10) + '...'
  }
  // 否则返回完整的第一行
  return firstLine
}

const getThemeClass = (index: number) => {
  const themes = ['pink-theme', 'blue-theme', 'yellow-theme']
  return themes[index % themes.length]
}

const getTagType = (index: number) => {
  const types = ['danger', 'info', 'warning']
  return types[index % types.length]
}

const handleEdit = (record: ReviewRecord) => {
  openNewPage(record.rtId, record)
}

const handleDelete = async (record: ReviewRecord) => {
  try {
    // 使用 ElMessageBox 显示确认对话框
    await ElMessageBox.confirm(
      '确定要删除这条记录吗？',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
    )

    // 用户确认后，调用删除接口
    const response = await request.get(`/reviewRecord/delete?id=${record.id}`)
    if (response.data.code === 200) {
      ElMessage.success('删除成功')
      // 重新获取列表
      fetchReviewRecords()
    } else {
      ElMessage.error(response.data.msg || '删除失败')
    }
  } catch (error) {
    if (error !== 'cancel') {  // 用户点击取消按钮不显示错误提示
      console.error('Failed to delete review record:', error)
      ElMessage.error(error instanceof Error ? error.message : '删除失败')
    }
  }
}

const handleDeleteType = async (type: ReviewType) => {
  try {
    await ElMessageBox.confirm(
      '确定要删除这个分类吗？删除后，该分类下的所有记录也会被删除。',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
    )

    const response = await request.get(`/reviewType/delete?id=${type.id}`)
    if (response.data.code === 200) {
      ElMessage.success('删除成功')
      fetchReviewRecords()
    } else {
      ElMessage.error(response.data.msg || '删除失败')
    }
  } catch (error) {
    if (error !== 'cancel') {
      console.error('Failed to delete review type:', error)
      ElMessage.error(error instanceof Error ? error.message : '删除失败')
    }
  }
}

const startEditing = (type: ReviewType) => {
  editingTypeId.value = type.id
  editingTypeName.value = type.name
}

const handleTypeNameChange = async () => {
  if (!editingTypeId.value || !editingTypeName.value.trim()) {
    editingTypeId.value = null
    return
  }

  try {
    const formData = new FormData()
    formData.append('id', String(editingTypeId.value))
    formData.append('name', editingTypeName.value.trim())

    const response = await request.post('/reviewType/change', formData)
    if (response.data.code === 200) {
      ElMessage.success('修改成功')
      fetchReviewRecords()
    } else {
      ElMessage.error(response.data.msg || '修改失败')
    }
  } catch (error) {
    console.error('Failed to update type name:', error)
    ElMessage.error(error instanceof Error ? error.message : '修改失败')
  } finally {
    editingTypeId.value = null
  }
}
</script>

<template>
  <div class="review-section">
    <div class="review-header">
      <div class="header-icon">📝</div>
      <div class="header-title">复盘记录</div>
      <div class="header-divider"></div>
    </div>
    <div v-if="loading" class="loading">
      加载中...
    </div>
    <div v-else class="review-content">
      <div class="cards-container">
        <div
          v-for="(type, index) in reviewTypes"
          :key="type.id"
          class="review-card"
          :class="getThemeClass(index)"
        >
          <div class="card-header">
            <div class="card-header-content">
              <template v-if="editingTypeId === type.id">
                <el-input
                  v-model="editingTypeName"
                  size="small"
                  class="edit-input"
                  @blur="handleTypeNameChange"
                  @keyup.enter="handleTypeNameChange"
                />
              </template>
              <template v-else>
                <span
                  class="type-name"
                  @click="startEditing(type)"
                >{{ type.name }}</span>
              </template>
              <el-tag size="small" :type="getTagType(index)" class="count-tag">
                {{ type.reviewRecordList.length }}
              </el-tag>
            </div>
            <div class="card-header-actions">
              <el-button
                class="action-btn"
                text
                :icon="Delete"
                @click="handleDeleteType(type)"
              />
            </div>
          </div>
          <div class="card-content">
            <template v-if="type.reviewRecordList.length > 0">
              <div
                v-for="record in type.reviewRecordList"
                :key="record.id"
                class="review-item"
              >
                <div class="review-item-content">
                  <div class="bullet">•</div>
                  <span class="date">@{{ formatArrayDate(record.date) }}</span>
                  <div class="item-content">{{ truncateContent(record.content) }}</div>
                </div>
                <div class="review-item-actions">
                  <el-button
                    class="action-btn"
                    text
                    :icon="Edit"
                    @click="handleEdit(record)"
                  />
                  <el-button
                    class="action-btn"
                    text
                    :icon="Delete"
                    @click="handleDelete(record)"
                  />
                </div>
              </div>
            </template>
            <div class="add-button">
              <el-button text type="primary" @click="openNewPage(type.id)">+ 新页面</el-button>
            </div>
          </div>
        </div>
      </div>

      <div class="add-category">
        <div class="new-group-container">
          <el-button class="add-btn" text @click="showNewGroupInput = !showNewGroupInput">
            <el-icon class="add-icon"><Plus /></el-icon>
          </el-button>
          <div v-if="showNewGroupInput" class="new-group-input">
            <el-input
              v-model="newGroupName"
              placeholder="输入分组名称"
              size="small"
              @keyup.enter="addNewGroup"
            >
              <template #append>
                <el-button type="primary" @click="addNewGroup">完成</el-button>
              </template>
            </el-input>
          </div>
        </div>
        <div class="add-text">隐藏群组</div>
        <div class="add-avatar">
          <el-avatar size="small">无分身</el-avatar>
        </div>
      </div>
    </div>

    <el-drawer
      v-model="showNewPage"
      direction="rtl"
      size="67%"
      :show-close="false"
      :with-header="false"
      class="new-page-drawer"
    >
      <div class="drawer-content">
        <div class="drawer-header">
          <div class="header-left">
            <el-button
              class="close-btn"
              text
              @click="closeNewPage"
            >
              <el-icon><ArrowRight /></el-icon>
            </el-button>
            <el-date-picker
              v-model="newPageDate"
              type="date"
              format="YYYY/MM/DD"
              value-format="YYYY-MM-DD"
              placeholder="选择日期"
              size="small"
              class="date-picker"
            />
          </div>
          <div class="header-right">
            <el-button type="primary" @click="saveNewPage">
              {{ isEditing ? '保存' : '发布' }}
            </el-button>
          </div>
        </div>
        <div class="editor-container">
          <md-editor
            v-model="newPageContent"
            :height="'100%'"
            :toolbars="[
              'bold',
              'italic',
              'strikeThrough',
              'title',
              'quote',
              'code',
              'orderedList',
              'unorderedList',
              'link',
              'image',
              'table'
            ]"
            language="zh-CN"
            @onUploadImg="handleImageUpload"
          />
        </div>
      </div>
    </el-drawer>
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
  justify-content: space-between;
  gap: 8px;
  font-size: 14px;
  font-weight: 500;
  margin-bottom: 16px;
  padding: 8px;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.card-header:hover {
  background-color: rgba(64, 158, 255, 0.1);
}

.card-header-content {
  display: flex;
  align-items: center;
  gap: 8px;
  white-space: nowrap;
}

.card-header-actions {
  display: none;
}

.card-header:hover .card-header-actions {
  display: flex;
  gap: 4px;
}

.card-header .action-btn:hover {
  color: #F56C6C;
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
  align-items: flex-start;
  justify-content: space-between;
  gap: 12px;
  color: #666;
  font-size: 14px;
  margin-bottom: 12px;
  padding: 8px;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.review-item:hover {
  background-color: rgba(64, 158, 255, 0.1);
}

.review-item-content {
  display: flex;
  align-items: flex-start;
  gap: 12px;
  flex: 1;
  min-width: 0;
}

.review-item-actions {
  display: none;
  gap: 4px;
}

.review-item:hover .review-item-actions {
  display: flex;
}

.action-btn {
  padding: 2px;
}

.action-btn:hover {
  color: #409EFF;
}

.action-btn:last-child:hover {
  color: #F56C6C;
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
  color: #333;
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

.loading {
  text-align: center;
  padding: 40px;
  color: #999;
}

.new-group-container {
  position: relative;
}

.new-group-input {
  position: absolute;
  right: 40px;
  top: 0;
  width: 200px;
  background: white;
  border-radius: 4px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  z-index: 1;
}

.new-group-input :deep(.el-input-group__append) {
  padding: 0;
}

.new-group-input :deep(.el-button) {
  border: none;
  margin: 0;
  padding: 0 12px;
}

.new-page-drawer :deep(.el-drawer__body) {
  padding: 0;
  overflow: hidden;
}

.drawer-content {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.drawer-header {
  padding: 16px;
  border-bottom: 1px solid #e4e7ed;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header-left {
  display: flex;
  align-items: center;
  gap: 16px;
}

.close-btn {
  font-size: 20px;
}

.date-picker {
  width: 130px;
}

.editor-container {
  flex: 1;
  overflow: hidden;
  padding: 20px;
}

.editor-container :deep(.md-editor) {
  height: 100%;
  border: none;
}

.editor-container :deep(.md-editor-toolbar) {
  border-bottom: 1px solid #e4e7ed;
}

.type-name {
  cursor: pointer;
  padding: 4px 8px;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.type-name:hover {
  background-color: rgba(64, 158, 255, 0.1);
}

.edit-input {
  width: 120px;
}

.edit-input :deep(.el-input__inner) {
  height: 28px;
}
</style>
