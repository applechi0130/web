<template>
  <div>
    <h2>註冊</h2>
    <form id="searchForm" @submit.prevent="onRegister">
      <label>電話：</label>
      <input v-model="phone" type="text" required maxlength="10" />

      <label>使用者名稱：</label>
      <input v-model="userName" type="text" required maxlength="10" />

      <label>Email：</label>
      <input v-model="email" type="email" required />

      <label>密碼：</label>
      <input v-model="password" type="password" required />

      <label>確認密碼：</label>
      <input v-model="confirmPassword" type="password" required />

      <button type="submit">註冊</button>
    </form>

    <div v-if="errorMsg" style="color: red; max-width: 600px; margin: 10px auto;">{{ errorMsg }}</div>
    <div v-if="successMsg" style="color: green; max-width: 600px; margin: 10px auto;">{{ successMsg }}</div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const phone = ref('')
const userName = ref('')
const email = ref('')
const password = ref('')
const confirmPassword = ref('')
const errorMsg = ref('')
const successMsg = ref('')

const onRegister = async () => {
  errorMsg.value = ''
  successMsg.value = ''

  if (password.value !== confirmPassword.value) {
    errorMsg.value = '密碼與確認密碼不相符'
    return
  }

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

<style scoped>
#searchForm {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  padding: 20px;
  background-color: #f7f9fc;
  border: 1px solid #dfe3e8;
  border-radius: 10px;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  margin: auto;
}

#searchForm input,
#searchForm select {
  padding: 8px;
  width: 100%;
  border: 1px solid #cbd5e0;
  border-radius: 6px;
  background-color: #fff;
}

#searchForm button {
  grid-column: span 2;
  padding: 12px;
  font-size: 16px;
  color: #fff;
  background-color: #5a67d8;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}

#searchForm button:hover {
  background-color: #4c51bf;
}

#searchForm label {
  font-weight: 500;
  color: #333;
  position: relative;
  text-align: left; /* 所有標籤靠左對齊 */
}
</style>
