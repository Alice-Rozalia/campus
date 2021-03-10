<template>
  <header>
    <div class="container">
      <router-link to="/">
        <img src="@/assets/logo.jpg" alt="logo" class="logo"/>
      </router-link>

      <div class="search" v-if="isIndex">
        <el-input placeholder="ROG 幻15">
          <template #append>
            <el-button icon="el-icon-search"></el-button>
          </template>
        </el-input>
      </div>
      <div class="header-right">
        <div v-if="!isLogin">
          <router-link to="/login" class="login-link-text">登录 | 注册</router-link>
        </div>
        <div v-else class="login-user-info">
          <span>{{loginUser.username}}</span>
          <el-dropdown>
            <el-badge is-dot type="danger">
              <el-avatar class="right-menu-item" :size="50" :src="loginUser.avatar"></el-avatar>
            </el-badge>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>
                  <router-link to="/my_center">个人中心</router-link>
                </el-dropdown-item>
                <el-badge :max="99" :value="5" type="danger">
                  <el-dropdown-item>
                    <router-link to="/message">消息中心</router-link>
                  </el-dropdown-item>
                </el-badge>
                <el-dropdown-item>
                  <router-link to="/release_goods">发布商品</router-link>
                </el-dropdown-item>
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
import { defineComponent, onBeforeMount, ref, watch, nextTick } from 'vue'
import { useRoute } from 'vue-router'

export default defineComponent({
  name: 'Header',
  setup() {
    const isLogin = ref(false)
    const loginUser = ref({})
    const isIndex = ref(true)
    const route = useRoute()

    watch(
      route,
      async ({ path }: any, prevRoute: unknown): Promise<void> => {
        await nextTick()
        path !== '/' ? isIndex.value = false : isIndex.value = true
      },
      { immediate: true }
    )

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
      window.sessionStorage.removeItem('user')
      window.sessionStorage.removeItem('token')
      isLogin.value = false
    }

    return {
      isLogin,
      loginUser,
      isIndex,
      logout
    }
  }
})
</script>

<style lang="less" scoped>
  @import "../styles/header.less";
</style>
