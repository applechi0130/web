<template>
  <div>
    <h2>註冊</h2>

    <!-- TODO:綁定事件 submit，透過 .prevent 修飾符阻止表單預設送出，改用 onRegister 方法處理。-->
    <form id="searchForm" @submit.prevent="onRegister">
      <label>電話：</label>
      <input v-model="phone" type="text" required maxlength="10" />
      <!-- v-model="phone" 雙向綁定變數 phone，required 表示必填，限制最大長度10字元 -->

      <label>使用者名稱：</label>
      <input v-model="userName" type="text" required maxlength="10" />

      <label>Email：</label>
      <input v-model="email" type="email" required />

      <label>密碼：</label>
      <input v-model="password" type="password" required />

      <label>確認密碼：</label>
      <input v-model="confirmPassword" type="password" required />

      <button type="submit">註冊</button> <!-- 表單提交按鈕，點擊會觸發 submit 事件。 -->
    </form>

    <!-- 顯示錯誤訊息或成功訊息，並限定最大寬度與置中。 -->
    <div :class="['message', errorMsg ? 'error' : '', successMsg ? 'success' : '']" v-if="errorMsg || successMsg">
      {{ errorMsg || successMsg }}
    </div>
  </div>
</template>

<script setup>
// TODO:從 Vue 引入 ref 函式，建立響應式變數。
import { ref } from 'vue'
import axios from 'axios'

// TODO:定義表單欄位的響應式變數，初始為空字串；另外定義錯誤與成功訊息變數，初始無訊息。
const phone = ref('')
const userName = ref('')
const email = ref('')
const password = ref('')
const confirmPassword = ref('')
const errorMsg = ref('')
const successMsg = ref('')

// TODO:定義註冊送出函式，開始時先清空錯誤與成功訊息。
const onRegister = async () => {
  errorMsg.value = ''
  successMsg.value = ''

  if (password.value !== confirmPassword.value) {
    errorMsg.value = '密碼與確認密碼不相符'
    return
  }

  // 使用 axios 向伺服器發送 POST 請求，送出註冊資料。
  try {
    await axios.post('http://localhost:8080/register', {
      phone: phone.value,
      userName: userName.value,
      email: email.value,
      password: password.value,
    })
    successMsg.value = '註冊成功'
    phone.value = ''
    userName.value = ''
    email.value = ''
    password.value = ''
    confirmPassword.value = ''
  } catch (err) {
    errorMsg.value = err.response?.data || '註冊失敗'
  }
}
</script>