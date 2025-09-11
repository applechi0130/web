<script setup>
import HelloWorld from './components/HelloWorld.vue'
import Login from './components/Login.vue'
import Register from './components/Register.vue'

import { ref } from 'vue'
const showLogin = ref(true)

const toggleForm = () => {
  showLogin.value = !showLogin.value
}
</script>

<template>
  <div>
    <!-- <a href="https://vite.dev" target="_blank">
      <img src="/vite.svg" class="logo" alt="Vite logo" />
    </a>
    <a href="https://vuejs.org/" target="_blank">
      <img src="./assets/vue.svg" class="logo vue" alt="Vue logo" />
    </a> -->

    <!-- <HelloWorld msg="Vite + Vue" /> -->
    <HelloWorld msg="會員系統示範" />

    <div style="margin-top: 2em;">
      <button @click="toggleForm" style="margin-bottom:1em;">
        {{ showLogin ? '切換到註冊' : '切換到登入' }}
      </button>
      <Login v-if="showLogin" />
      <Register v-else />
    </div>
  </div>
</template>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}

.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}

.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}

button {
  padding: 0.5em 1em;
  cursor: pointer;
}
</style>
