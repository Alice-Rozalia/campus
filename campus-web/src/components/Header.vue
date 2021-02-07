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
        <router-link to="/login" class="right-menu-item">登录</router-link>
        <router-link to="/register" class="right-menu-item">注册</router-link>
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
