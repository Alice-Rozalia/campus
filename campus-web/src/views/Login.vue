<template>
  <div class="login-container">
    <!-- 星星 -->
    <div class="stars">
      <div class="star" :ref="star" v-for="(item, index) in starCount" :key="index"></div>
    </div>

    <div class="login-box">
      <div class="login-title" v-if="isLogin">登录</div>
      <div class="login-title" v-else>注册</div>

      <login-form :loginForm="loginForm" :rules="rules" v-if="isLogin"/>
      <register-form :registerUser="registerUser" :rules="rules" v-else/>

      <div class="login-box-footer">
        <span v-if="isLogin">还没有账号？<span class="footer-link" @click="changeForm">前往注册</span></span>
        <span v-else>已有账号？<span class="footer-link" @click="changeForm">去登录</span></span>
        <span>忘记密码？<span class="footer-link">立即找回</span></span>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs, watch } from 'vue'
import { loginForm, registerUser, rules } from '@/utils/validators'
import LoginForm from '@/components/loginRegister/LoginForm.vue'
import RegisterForm from '@/components/loginRegister/RegisterForm.vue'

export default defineComponent({
  name: 'Login',
  components: {
    LoginForm,
    RegisterForm
  },
  setup: function () {
    const state = reactive({
      starCount: 800,   // 星星数量
      distance: 800,    // 间距
      isLogin: true
    })

    let stars: any[] = []
    const star = (el: HTMLElement) => {
      stars.push(el)
    }

    onMounted(() => {
      stars.forEach((item: HTMLElement) => {
        let spend = 0.2 + Math.random() * 1
        let thisDistance = state.distance + Math.random() * 300
        item.style.transformOrigin = `0 0 ${thisDistance}px`
        item.style.transform = `translate3d(0,0,-${thisDistance}px) rotateY(${Math.random() * 360}deg) rotateX(${Math.random() * -50}deg) scale(${spend},${spend})`
      })
    })

    const changeForm = () => {
      state.isLogin = !state.isLogin
    }

    return {
      ...toRefs(state),
      star,
      loginForm,
      rules,
      registerUser,
      changeForm
    }
  }
})
</script>

<style lang="less" scoped>
  @import "../styles/login.less";
</style>
