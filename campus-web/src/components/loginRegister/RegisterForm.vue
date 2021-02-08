<template>
  <el-form :model="registerUser" :rules="rules" ref="registerFormRef" class="register-form">
    <el-form-item prop="username">
      <el-input v-model="registerUser.username" prefix-icon="el-icon-user" placeholder="用户名"/>
    </el-form-item>
    <el-form-item prop="password">
      <el-input v-model="registerUser.password" prefix-icon="el-icon-lock" type="password" show-password
                placeholder="密码"/>
    </el-form-item>
    <el-form-item prop="password2">
      <el-input v-model="registerUser.password2" prefix-icon="el-icon-lock" type="password" show-password
                placeholder="确认密码"/>
    </el-form-item>
    <el-form-item>
      <el-button @click="register('registerFormRef')" :loading="loading" type="primary" style="width: 100%">注册
      </el-button>
    </el-form-item>

    <touch-verify-code v-if="isShow" class="code" @success="verifySuccess" @failed="verifyFailed"/>
  </el-form>
</template>

<script lang="ts">
import { defineComponent, ref, getCurrentInstance, onBeforeMount } from 'vue'
import TouchVerifyCode from '@/components/code/TouchVerifyCode.vue'
import { warnMessage, successMessage } from '@/utils/message'
import { registerApi } from '@/api/user'

export default defineComponent({
  name: 'RegisterForm',
  components: {
    TouchVerifyCode
  },
  props: {
    registerUser: {
      type: Object,
      required: true
    },
    rules: {
      type: Object,
      required: true
    }
  },
  setup(props) {
    let vm: any
    const loading = ref(false)
    const isShow = ref(false)

    // 获取组件实例
    onBeforeMount(() => {
      vm = getCurrentInstance()
    })

    const register = (formName: string) => {
      vm.refs[formName].validate((valid: boolean) => {
        if (!valid) return
        loading.value = true
        isShow.value = true
      })
    }

    const verifySuccess = async (time: number) => {
      if (time > 5) {
        warnMessage('验证超时！')
        loading.value = false
        isShow.value = false
        return
      }

      const { data } = await registerApi(props.registerUser)
      if (data.success) {
        successMessage(data.message)
      }
      loading.value = false
      isShow.value = false
    }

    const verifyFailed = () => {
      loading.value = false
      isShow.value = false
    }

    return {
      loading,
      isShow,
      register,
      verifyFailed,
      verifySuccess
    }
  }
})
</script>

<style lang="less" scoped>
  .register-form {
    position: relative;

    .code {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }
  }
</style>
