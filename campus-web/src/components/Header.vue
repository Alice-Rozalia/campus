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
          <el-dropdown>
            <el-avatar class="right-menu-item" :size="50" :src="loginUser.avatar"></el-avatar>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>个人中心</el-dropdown-item>
                <el-dropdown-item>账号设置</el-dropdown-item>
                <el-dropdown-item divided @click="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </div>
    </div>
  </header>
</template>

<script lang="ts">
import { defineComponent, onBeforeMount, ref } from 'vue'
import { logoutApi } from '@/api/user'
import { successMessage } from '@/utils/message'

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

    const logout = async () => {
      const { data } = await logoutApi()
      if (data.success) {
        successMessage(data.message)
        window.sessionStorage.clear()
      }
    }

    return {
      isLogin,
      loginUser,
      logout
    }
  }
})
</script>

<style lang="less" scoped>
  @import "../styles/header.less";
</style>
