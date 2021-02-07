<template>
  <div class="login-container">
    <!-- 星星 -->
    <div class="stars">
      <div class="star" :ref="star" v-for="(item, index) in starCount" :key="index"></div>
    </div>

    <div class="login-box">
      <div class="login-title">登录</div>
      <el-form :model="loginForm" :rules="rules" ref="loginFormRef">
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" prefix-icon="el-icon-user" placeholder="用户名"/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="loginForm.password" prefix-icon="el-icon-lock" type="password" show-password
                    placeholder="密码"/>
        </el-form-item>
        <el-form-item>
          <el-button @click="login('loginFormRef')" :loading="loading" type="primary" style="width: 100%">登录</el-button>
        </el-form-item>
      </el-form>
      <div class="login-box-footer">
        <span>还没有账号？<router-link to="/register" class="footer-link">前往注册</router-link></span>
        <span>忘记密码？<router-link to="/register" class="footer-link">立即找回</router-link></span>
      </div>
    </div>

    <!-- 验证码 -->
    <touch-verify-code @success="onSuccess" v-if="isShow" @failed="onFailed" class="touch-verify-code"/>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs, getCurrentInstance, onBeforeMount } from 'vue'
import TouchVerifyCode from '@/components/code/TouchVerifyCode.vue'
import { loginForm, rules } from '@/utils/validators'
import { errorMessage } from '@/utils/message'
import { successNotification } from '@/utils/notification'
import { loginApi } from '@/api/user'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'Login',
  components: {
    TouchVerifyCode,
  },
  setup: function () {
    let vm: any
    const router = useRouter()

    const state = reactive({
      starCount: 800,   // 星星数量
      distance: 800,    // 间距
      isShow: false,
      loading: false
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

    // 获取组件实例
    onBeforeMount(() => {
      vm = getCurrentInstance()
    })

    const login = (formName: string): void => {
      vm.refs[formName].validate((valid: boolean) => {
        if (!valid) return
        state.isShow = true
        state.loading = true
      })
    }

    const onSuccess = async (time: number) => {
      const { data } = await loginApi(loginForm)
      if (data.success) {
        router.push('/')
        successNotification(data.message)
        window.sessionStorage.setItem('user', JSON.stringify(data.data.user))
      }
      state.isShow = false
      state.loading = false
    }

    const onFailed = (): void => {
      errorMessage("验证失败！")
      state.isShow = false
      state.loading = false
    }

    return {
      ...toRefs(state),
      star,
      login,
      onSuccess,
      onFailed,
      loginForm,
      rules
    }
  }
})
</script>

<style lang="less" scoped>
  @import "../styles/login.less";
</style>
