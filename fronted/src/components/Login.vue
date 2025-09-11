<template>
  <div>
    <h2>登入</h2>
    <form @submit.prevent="onLogin">
      <div>
        <label>電話：</label>
        <input v-model="phone" type="text" required />
      </div>
      <div>
        <label>密碼：</label>
        <input v-model="password" type="password" required />
      </div>
      <button type="submit">登入</button>
    </form>
    <div v-if="errorMsg" style="color: red;">{{ errorMsg }}</div>
    <div v-if="token" style="color: green;">登入成功，Token: {{ token }}</div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const phone = ref('')
const password = ref('')
const errorMsg = ref('')
const token = ref('')

const onLogin = async () => {
  errorMsg.value = ''
  token.value = ''
  try {
    const response = await axios.post('http://localhost:8080/login', {
      phone: phone.value,
      password: password.value
    })
    token.value = response.data.token
  } catch (err) {
    errorMsg.value = err.response?.data || '登入失敗'
  }
}
</script>
