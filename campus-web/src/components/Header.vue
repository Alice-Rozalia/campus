<template>
  <header>
    <div class="container">
      <img src="@/assets/logo.jpg" alt="logo" class="logo"/>
      <div class="search">
        <el-input placeholder="显卡RTX3060">
          <template #append>
            <el-button icon="el-icon-search"></el-button>
          </template>
        </el-input>
      </div>
      <div class="header-right">
        <el-avatar src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"></el-avatar>
        <span class="username">登录</span>
      </div>
    </div>
  </header>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'Header'
})
</script>

<style lang="less" scoped>
  @import "../styles/header.less";
</style>
