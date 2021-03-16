<template>
  <el-container class="admin-container">
    <!-- 左-侧边栏 -->
    <el-aside :width="isCollapse ? '64px' : '200px'">
      <!-- 侧边栏菜单区域 -->
      <el-menu background-color="#304156" text-color="#fff" active-text-color="#00a4ff" unique-opened
               :collapse="isCollapse" :collapse-transition="false" router :default-active="activePath">
        <el-submenu :index="item.id" v-for="item in menuList" :key="item.id">
          <!-- 一级菜单的模板区域 -->
          <template #title>
            <!-- 图标 -->
            <i :class="item.icon"></i>
            <!-- 文本 -->
            <span>{{ item.menuName }}</span>
          </template>

          <!-- 二级菜单 -->
          <el-menu-item :index="subitem.path" v-for="subitem in item.children" :key="subitem.id"
                        @click="saveNavState(subitem.path)">
            <template #title>
              <!-- 图标 -->
              <i class="el-icon-menu"></i>
              <!-- 文本 -->
              <span>{{ subitem.menuName }}</span>
            </template>
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>

    <!-- 右-页面主体区域 -->
    <el-container>
      <!-- 头部区域 -->
      <el-header>
        <!-- 折叠菜单 -->
        <i class="el-icon-s-fold zhedie" @click="isCollapse = !isCollapse"></i>
        <div class="title">
          <span>后台管理系统</span>
        </div>
        <div class="user">
          <span class="username">{{ loginUser.username }}</span>
          <el-dropdown>
            <div class="person-photo">
              <img :src="loginUser.avatar" class="user-avatar" alt="avatar">
            </div>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>
                  <router-link to="/">前台首页</router-link>
                </el-dropdown-item>
                <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>
      <!-- 右侧内容主体 -->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs, onMounted, onBeforeMount } from 'vue'
import { useRouter } from 'vue-router'
import menuList from '@/utils/menu'

export default defineComponent({
  name: 'Admin',
  setup() {
    const router = useRouter()

    const state = reactive({
      isCollapse: false,
      activePath: 'admin',
      loginUser: {}
    })

    onBeforeMount(() => {
      const user: any = window.sessionStorage.getItem('user')
      state.loginUser = JSON.parse(user)
      getActive()
    })

    // 保存链接的激活状态
    const saveNavState = (path: string) => {
      window.sessionStorage.setItem('activePath', path)
      state.activePath = path
    }

    // 获取链接激活状态
    const getActive = () => {
      const path: any = window.sessionStorage.getItem('activePath')
      state.activePath = path
      if (state.activePath === null) {
        state.activePath = 'admin'
      }
    }

    // 退出登陆
    const logout = () => {
      window.sessionStorage.clear()
      router.push('/')
    }

    return {
      ...toRefs(state),
      saveNavState,
      logout,
      menuList
    }
  }
})
</script>

<style lang="less" scoped>
@import "../../styles/admin/admin.less";
</style>

<style>
.el-main .el-card {
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.15) !important;
}
</style>
