<script setup lang="ts">
import { useI18n } from 'vue-i18n'

const { locale } = useI18n()

const toggleLanguage = () => {
  locale.value = locale.value === 'zh' ? 'en' : 'zh'
}
</script>

<template>
  <div class="language-switcher" @click="toggleLanguage">
    {{ locale === 'zh' ? '中' : 'EN' }}
  </div>
  <div class="app-container">
    <RouterView />
  </div>
</template>

<style>
/* 全局样式 */
:root {
  font-family: 'JetBrains Mono', 'Fira Code', 'Cascadia Code', 'Source Code Pro', Menlo, Monaco, Consolas, 'Courier New', monospace;
}

/* 确保所有文本元素继承字体 */
body, button, input, select, textarea {
  font-family: inherit;
}

html, body {
  margin: 0;
  padding: 0;
  width: 100%;
  overflow-x: hidden;
}

body {
  min-height: 100vh;
}

.app-container {
  width: 100%;
  min-height: 100vh;
  position: relative;
}

#app {
  margin: 0;
  padding: 0;
  width: 100%;
  min-height: 100vh;
  position: relative;
}

.language-switcher {
  position: fixed;
  top: 20px;
  right: 180px;
  z-index: 1000;
  background: rgba(0, 0, 0, 0.6);
  color: white;
  padding: 8px 12px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: background-color 0.3s;
  display: flex;
  align-items: center;
  gap: 4px;
  min-width: 40px;
  justify-content: center;
}

.language-switcher:hover {
  background: rgba(0, 0, 0, 0.8);
}
</style>

<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style>
