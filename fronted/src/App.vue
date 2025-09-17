<script setup>

// TODO:從 components 資料夾引入 HelloWorld、Login 和 Register 三個元件。
import HelloWorld from './components/HelloWorld.vue'
import Login from './components/Login.vue'
import Register from './components/Register.vue'

// TODO:從 Vue 引入 ref 函式，建立一個響應式變數 showLogin，初始值為 true，預設會顯示登入元件(Login)。
import { ref } from 'vue'
const showLogin = ref(true)

// NOTE:建立一個函式 toggleForm，用來切換 showLogin 的值。
const toggleForm = () => {
  showLogin.value = !showLogin.value
}
</script>

<template>
  <div>
    <!-- 引用 HelloWorld 元件，傳入字串 prop msg，顯示「會員系統示範」作為標題 -->
    <HelloWorld msg="會員系統示範" />

    <!-- 點擊時執行 toggleForm 切換登入或註冊頁面，按鈕文字根據 showLogin 狀態顯示 -->
    <div style="margin-top: 2em;">

      <button class="change" @click="toggleForm" style="margin-bottom:1em;">
        {{ showLogin ? '切換到註冊' : '切換到登入' }}
      </button>
      <!-- 根據 showLogin 狀態條件渲染 Login 或 Register 元件 -->
      <Login v-if="showLogin" />
      <Register v-else />

    </div>
  </div>
</template>

<style scoped>
div {
  text-align: center;
  margin-top: 1em;
}

button.change {
  padding: 0.5em 1em;
  cursor: pointer;
  color: black;
  /* 字體顏色 */
  background-color: rgb(237, 252, 26);
  /* 按鈕底色 */
  border: 1px solid black;
  /* 可選，移除邊框 */
  border-radius: 4px;
  /* 可選，圓角 */
}

button.change:hover {
  background-color: gray;
  /* 滑鼠懸停時的底色 */
  color: white;
  /* 滑鼠懸停時的字體顏色 */
}
</style>
