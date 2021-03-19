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

  </el-form>
</template>

<script lang="ts">
import { defineComponent, ref, getCurrentInstance, onBeforeMount } from 'vue'
import { successMessage } from '@/utils/message'
import { registerApi } from '@/api/user'

export default defineComponent({
  name: 'RegisterForm',
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

    // 获取组件实例
    onBeforeMount(() => {
      vm = getCurrentInstance()
    })

    const register = (formName: string) => {
      vm.refs[formName].validate((valid: boolean) => {
        if (!valid) return
        loading.value = true
        registerApi(props.registerUser).then(({ data }) => {
          if (data.success) {
            successMessage(data.message)
            loading.value = false
          }
        }).catch(err => {
          loading.value = false
        })
      })
    }

    return {
      loading,
      register
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
