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
        <div v-if="!isLogin">
          <router-link to="/login">登录</router-link>
          <router-link to="/register" class="right-menu-item">注册</router-link>
        </div>
        <div v-else class="login-user-info">
          <span>{{loginUser.username}}</span>
          <el-avatar class="right-menu-item" :src="loginUser.avatar"></el-avatar>
        </div>
      </div>
    </div>
  </header>
</template>

<script lang="ts">
import { defineComponent, onBeforeMount, ref } from 'vue'

export default defineComponent({
  name: 'Header',
  setup() {
    const isLogin = ref(false)
    const loginUser = ref({})

    onBeforeMount(() => {
      const user = window.sessionStorage.getItem('user')
      if (user) {
        isLogin.value = true
        loginUser.value = JSON.parse(user)
      } else {
        isLogin.value = false
      }
    })

    return {
      isLogin,
      loginUser
    }
  }
})
</script>

<style lang="less" scoped>
  @import "../styles/header.less";
</style>
