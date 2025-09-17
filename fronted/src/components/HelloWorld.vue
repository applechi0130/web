<script setup>

import { ref } from 'vue'

// TODO:之後在 <template> 中就可以用 {{ msg }} 來顯示傳遞進來的值。
defineProps({
  msg: String,  // 宣告了一個父元件傳進來的 props，名字是 msg，型別是 String。
})

// TODO:建立一個響應式變數 count，初始值是 0。
const count = ref(0)

</script>


<template>
  <h1>{{ msg }}</h1> <!-- 顯示傳遞進來的 msg -->

  <button class="bb" type="button" @click="count++">目前點擊次數：<span class="count-red">{{ count }}</span></button>

</template>


<style scoped>
.read-the-docs {
  color: #888;
}

/* 新增紅色字體樣式 */
.count-red {
  color: red;
}

.bb {
  font-size: 1.2em;
  /* 調整字體大小 */
  border-radius: 8px;
  /* 圓角 */
  padding: 0.5em;
  /* 內距 */
  text-align: center;
  /* 文字置中 */
  margin-top: 1em;
  /* 上方外距 */
  cursor: pointer;
  /* 滑鼠指標變成手指 */
  background: #f8f6f6;
}
</style>
