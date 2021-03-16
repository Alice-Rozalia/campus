<template>
  <header>
    <div class="container">
      <router-link to="/">
        <img src="@/assets/logo.jpg" alt="logo" class="logo"/>
      </router-link>

      <div class="search" v-if="isIndex">
        <el-input placeholder="ROG 幻15" v-model="search">
          <template #append>
            <el-button icon="el-icon-search" @click="searchGoods"></el-button>
          </template>
        </el-input>
      </div>
      <div class="header-right">
        <div v-if="!isLogin">
          <router-link to="/login" class="login-link-text">登录 | 注册</router-link>
        </div>
        <div v-else class="login-user-info">
          <el-badge is-dot type="danger" v-if="unreadLetter > 0">
            <span>{{ loginUser.username }}</span>
          </el-badge>
          <span v-else>{{ loginUser.username }}</span>
          <el-dropdown>
            <el-avatar class="right-menu-item" :size="50" :src="loginUser.avatar"></el-avatar>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>
                  <router-link to="/my_center">个人中心</router-link>
                </el-dropdown-item>
                <el-badge :max="99" :value="unreadLetter" type="danger">
                  <el-dropdown-item>
                    <router-link to="/message">消息中心</router-link>
                  </el-dropdown-item>
                </el-badge>
                <el-dropdown-item>
                  <router-link to="/release_goods">发布商品</router-link>
                </el-dropdown-item>
                <el-dropdown-item>
                  <router-link to="/setting">账号设置</router-link>
                </el-dropdown-item>
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
import { useStore } from 'vuex'
import { warningNotification } from '@/utils/notification'
import { fetchUnreadLettersApi } from '@/api/message'

export default defineComponent({
  name: 'Header',
  setup() {
    const isLogin = ref(false)
    const loginUser = ref({})
    const isIndex = ref(true)
    const search = ref('')
    const unreadLetter = ref(0)
    const route = useRoute()
    const store = useStore()

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
        getUnreadLetterCount()
      } else {
        isLogin.value = false
      }
    })

    // 获取未读私信数量
    const getUnreadLetterCount = async () => {
      const { data } = await fetchUnreadLettersApi()
      if (data.success) {
        unreadLetter.value = data.data.unreadLetter
      }
    }

    // 搜索商品
    const searchGoods = () => {
      if (search.value.trim() === '') {
        return warningNotification('搜索关键字不能为空！')
      }
      store.dispatch('keyChange', search.value)
    }

    // 退出登录
    const logout = async () => {
      window.sessionStorage.removeItem('user')
      window.sessionStorage.removeItem('token')
      isLogin.value = false
    }

    return {
      isLogin,
      loginUser,
      isIndex,
      search,
      unreadLetter,
      logout,
      searchGoods
    }
  }
})
</script>

<style lang="less" scoped>
@import "../styles/header.less";
</style>
