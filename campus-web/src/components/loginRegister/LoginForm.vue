<template>
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
</template>

<script lang="ts">
import { defineComponent, ref, getCurrentInstance, onBeforeMount } from 'vue'
import { loginApi } from '@/api/user'
import { successNotification } from '@/utils/notification'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'LoginForm',
  props: {
    loginForm: {
      type: Object,
      required: true
    },
    rules: {
      type: Object,
      required: true
    }
  },
  setup(props) {
    const router = useRouter()

    let vm: any
    const loading = ref(false)

    // 获取组件实例
    onBeforeMount(() => {
      vm = getCurrentInstance()
    })

    const login = (formName: string): void => {
      vm.refs[formName].validate(async (valid: boolean) => {
        if (!valid) return
        loading.value = true
        loginApi(props.loginForm).then(({ data }: any) => {
          if (data.success) {
            router.push('/')
            successNotification(data.message)
            window.sessionStorage.setItem('user', JSON.stringify(data.data.user))
            loading.value = false
          } else {
            loading.value = false
          }
        }).catch(err => {
          loading.value = false
        })
      })
    }

    return {
      loading,
      login
    }
  }
})
</script>
