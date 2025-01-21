<template>
  <div class="login-container">
    <div class="login-content">
      <div class="left-section">
        <div class="welcome-text">
          <h1>欢迎加入</h1>
          <p>创建您的账户</p>
        </div>
      </div>
      <div class="right-section">
        <div class="login-form-container">
          <h2>注册</h2>
          <form @submit.prevent="handleSubmit" class="login-form">
            <div class="form-group">
              <label for="username">用户名</label>
              <input
                id="username"
                v-model="username"
                type="text"
                maxlength="50"
                required
                placeholder="请输入用户名"
                :class="{ 'error': usernameError }"
              >
              <span class="error-message" v-if="usernameError">{{ usernameError }}</span>
            </div>

            <div class="form-group">
              <label for="password">密码</label>
              <input
                id="password"
                v-model="password"
                type="password"
                maxlength="20"
                required
                placeholder="请输入密码"
                :class="{ 'error': passwordError }"
              >
              <span class="error-message" v-if="passwordError">{{ passwordError }}</span>
            </div>

            <div class="form-group">
              <label for="confirmPassword">确认密码</label>
              <input
                id="confirmPassword"
                v-model="confirmPassword"
                type="password"
                maxlength="20"
                required
                placeholder="请再次输入密码"
                :class="{ 'error': confirmPasswordError }"
              >
              <span class="error-message" v-if="confirmPasswordError">{{ confirmPasswordError }}</span>
            </div>

            <button type="submit" class="login-button">注册</button>

            <div class="login-link">
              已有账户？ <router-link to="/login">立即登录</router-link>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { register } from '@/api/auth'
import { ElMessage } from 'element-plus'

const router = useRouter()
const username = ref('')
const password = ref('')
const confirmPassword = ref('')
const usernameError = ref('')
const passwordError = ref('')
const confirmPasswordError = ref('')

const handleSubmit = async () => {
  // Reset errors
  usernameError.value = ''
  passwordError.value = ''
  confirmPasswordError.value = ''

  // Validate
  if (username.value.length === 0) {
    usernameError.value = '请输入用户名'
    return
  }

  if (password.value.length === 0) {
    passwordError.value = '请输入密码'
    return
  }

  if (confirmPassword.value.length === 0) {
    confirmPasswordError.value = '请确认密码'
    return
  }

  if (password.value !== confirmPassword.value) {
    confirmPasswordError.value = '两次输入的密码不一致'
    return
  }

  try {
    await register({
      username: username.value,
      password: password.value
    })

    // 注册成功
    ElMessage({
      message: '注册成功，即将跳转到登录页面',
      type: 'success',
      duration: 2000
    })

    // 2秒后跳转到登录页
    setTimeout(() => {
      router.push('/login')
    }, 2000)
  } catch (error: any) {
    // 显示错误信息
    ElMessage({
      message: error.message || '注册失败',
      type: 'error',
      duration: 2000
    })
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.login-container {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  overflow: hidden;
}

.login-content {
  width: 100%;
  height: 100%;
  display: flex;
  background: white;
}

.left-section {
  flex: 1;
  background: linear-gradient(135deg,
    #E94B9C 0%,
    #B84B9C 50%,
    #8E4B9C 100%
  );
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  overflow: hidden;
}

.left-section::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background:
    linear-gradient(45deg, rgba(255, 255, 255, 0.1) 0%, transparent 100%),
    radial-gradient(circle at 50% 0%, rgba(255, 255, 255, 0.15) 0%, transparent 75%);
  animation: shine 8s linear infinite;
  z-index: 1;
}

@keyframes shine {
  0% {
    opacity: 0.5;
  }
  50% {
    opacity: 0.8;
  }
  100% {
    opacity: 0.5;
  }
}

.welcome-text {
  color: white;
  text-align: center;
  position: relative;
  z-index: 2;
}

.welcome-text h1 {
  font-size: min(4vw, 3rem);
  margin-bottom: 1rem;
  color: white;
}

.welcome-text p {
  font-size: min(2vw, 1.5rem);
  opacity: 0.9;
}

.right-section {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #fff;
}

.login-form-container {
  width: 100%;
  max-width: min(80%, 400px);
  padding: 0 2rem;
}

h2 {
  font-size: 2rem;
  color: #333;
  margin-bottom: 2rem;
  text-align: center;
}

.login-form {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

label {
  font-size: 1rem;
  font-weight: 500;
  color: #333;
}

input {
  padding: 1rem;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 1rem;
  transition: all 0.3s ease;
}

input:focus {
  outline: none;
  border-color: #B84B9C;
  box-shadow: 0 0 0 2px rgba(184, 75, 156, 0.2);
}

input.error {
  border-color: #ff4444;
}

.error-message {
  color: #ff4444;
  font-size: 0.875rem;
}

.login-button {
  background: linear-gradient(to right, #B84B9C, #8E4B9C);
  color: white;
  padding: 1rem;
  border: none;
  border-radius: 8px;
  font-size: 1.1rem;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s ease;
  margin-top: 1rem;
}

.login-button:hover {
  background: linear-gradient(to right, #E94B9C, #B84B9C);
  transform: translateY(-1px);
  box-shadow: 0 4px 15px rgba(184, 75, 156, 0.3);
}

.login-button:active {
  transform: translateY(0);
}

.login-link {
  text-align: center;
  margin-top: 1rem;
  font-size: 0.9rem;
  color: #666;
}

.login-link a {
  color: #B84B9C;
  text-decoration: none;
  font-weight: 500;
  transition: color 0.3s ease;
}

.login-link a:hover {
  color: #E94B9C;
}

@media (max-width: 768px) {
  .login-content {
    flex-direction: column;
  }

  .left-section, .right-section {
    flex: none;
  }

  .left-section {
    height: 35vh;
    min-height: 200px;
  }

  .right-section {
    height: 65vh;
  }

  .welcome-text h1 {
    font-size: min(8vw, 2.5rem);
  }

  .welcome-text p {
    font-size: min(4vw, 1.2rem);
  }

  .login-form-container {
    max-width: min(90%, 400px);
    padding: 0 1rem;
  }
}
</style>
