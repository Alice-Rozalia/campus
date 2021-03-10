<template>
  <div class="my-center-container">
    <Header/>

    <div class="main">
      <el-tabs type="card">
        <el-tab-pane>
          <template #label>
            我的收藏
          </template>

          <Follow/>
        </el-tab-pane>
        <el-tab-pane>
          <template #label>
            我的商品
          </template>

          <Goods/>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

import Header from '@/components/Header.vue'
import Follow from '@/components/MyCenter/Follow.vue'
import Goods from '@/components/MyCenter/Goods.vue'

export default defineComponent({
  name: 'MyCenter',
  components: {
    Header,
    Follow,
    Goods
  }
})
</script>

<style lang="less" scoped>
.my-center-container {
  background-color: #f2f2f2;
  min-height: 100vh;

  .main {
    width: 1000px;
    margin: 20px auto 0;
    background-color: #fff;
    border-radius: 3px;
    padding: 15px;
    min-height: 300px;
    box-sizing: border-box;
  }
}
</style>
