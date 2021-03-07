<template>
  <div class="admin-login">
    <el-card class="login-card">
      <div slot="header">
        <h1>后台管理系统</h1>
      </div>

      <el-form :model="loginForm" :rules="rules" ref="adminLoginFormRef">
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" prefix-icon="el-icon-user" placeholder="用户名"/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="loginForm.password" prefix-icon="el-icon-lock" type="password" show-password
                    placeholder="密码"/>
        </el-form-item>
        <el-form-item>
          <el-button @click="login('adminLoginFormRef')" :loading="loading" type="primary" style="width: 100%">登录
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs, getCurrentInstance, onBeforeMount } from 'vue'
import { rules } from '@/utils/validators'
import { adminLoginApi } from '@/api/user'
import { successNotification } from '@/utils/notification'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'AdminLogin',
  setup() {
    let vm: any

    const router = useRouter()

    const state = reactive({
      loginForm: {
        username: '',
        password: ''
      },
      loading: false,
    })

    onBeforeMount(() => {
      vm = getCurrentInstance()
    })

    const login = (formName: string): void => {
      vm.refs[formName].validate(async (valid: boolean) => {
        if (!valid) return
        state.loading = true

        const { data } = await adminLoginApi(state.loginForm)
        if (data.success) {
          successNotification(data.message)
          window.sessionStorage.setItem('user', JSON.stringify(data.data.user.user))
          window.sessionStorage.setItem('token', JSON.stringify(data.data.token))
          router.push('/admin')
        }
        state.loading = false
      })
    }

    return {
      ...toRefs(state),
      login,
      rules
    }
  }
})
</script>

<style lang="less" scoped>
.admin-login {
  min-height: 100vh;
  background-color: #f2f2f2;
  padding-top: 200px;

  .login-card {
    width: 450px;
    margin: 0 auto;

    h1 {
      height: 38px;
      text-align: center;
      font-weight: 700;
      font-size: 18px;
    }
  }
}
</style>
