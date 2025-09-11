<script setup>
import { ref } from 'vue'

defineProps({
  msg: String,
})

const count = ref(0)
</script>

<template>
  <h1>{{ msg }}</h1>

  <div class="card">
    <button type="button" @click="count++">目前點擊次數：{{ count }}</button>
    <!-- <p>
      請修改 <code>components/HelloWorld.vue</code> 以查看熱重載效果
    </p> -->
  </div>

</template>


<style scoped>
.read-the-docs {
  color: #888;
}
</style>
