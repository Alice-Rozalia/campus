<template>
  <div class="main-container">
    <Header/>

    <div class="main-warp">
      <Banner/>
      <br v-for="item in 40" :key="item"/>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import Header from '@/components/Header.vue'
import Banner from '@/components/Banner.vue'

export default defineComponent({
  name: 'Index',
  components: {
    Header,
    Banner
  }
})
</script>

<style lang="less" scoped>
  .main-container {
    height: 100%;
    background-color: #f8f8f9;
  }

  .main-warp {
    width: 1200px;
    margin: 0 auto;
  }
</style>
