<template>
  <div class="setting-container">
    <Header/>

    <div class="setting-main">
      <el-card>
        <el-form ref="setFormRef" :rules="setRules" :model="setForm" label-width="100px">

          <el-form-item label="地址" prop="address">
            <el-input v-model="setForm.address" clearable placeholder="地址..."/>
          </el-form-item>

          <el-form-item label="手机号" prop="phone">
            <el-input v-model="setForm.phone" clearable placeholder="手机号..."/>
          </el-form-item>

          <el-form-item label="短信验证码" prop="code">
            <el-input v-model="setForm.code" clearable placeholder="短信验证码...">
              <template #append>
                <el-button @click="sendCode" :disabled="isSend" v-text="sendText"/>
              </template>
            </el-input>
          </el-form-item>

          <el-form-item label="" prop="">
            <el-button @click="setUserInfo" type="primary">确认修改</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs, onMounted, getCurrentInstance, onBeforeMount } from 'vue'
import { setForm, setRules } from '@/utils/settingValidators'
import { sendCodeApi, setUserInfoApi } from '@/api/user'
import { useRouter } from 'vue-router'
import { successMessage } from '@/utils/message'
import Header from '@/components/Header.vue'

export default defineComponent({
  name: 'Setting',
  components: {
    Header
  },
  setup() {
    const user: any = window.sessionStorage.getItem('user')
    let vm: any
    const router = useRouter()

    let timer:any

    const state = reactive({
      isSend: false,
      sendText: '发送验证码',
      authTime: 60
    })

    // 获取组件实例
    onBeforeMount(() => {
      if (!user) return router.push('/')
      vm = getCurrentInstance()
    })

    onMounted(() => {
      setForm.value.address = JSON.parse(user).address
      setForm.value.phone = JSON.parse(user).phone
    })

    const sendCode = () => {
      vm.refs['setFormRef'].validateField('phone', async (error: any) => {
        if (error) return
        const { data } = await sendCodeApi({ phone: setForm.value.phone, type: 1 })
        if (data.success) {
          successMessage(data.message)
        }

        state.isSend = true
        if (state.authTime > 0) {
          timer = setInterval(() => {
            state.authTime--
            state.sendText = state.authTime + '秒后再次获取'
            if (state.authTime <= 0) {
              clearInterval(timer)
              state.isSend = false
              state.authTime = 60
              state.sendText = '发送验证码'
            }
          }, 1000)
        }
      })
    }

    const setUserInfo = () => {
      vm.refs['setFormRef'].validate(async (valid: boolean) => {
        if (!valid) return
        const { data } = await setUserInfoApi(setForm.value)
        if (data.success) {
          successMessage(data.message)
          return router.push('/')
        }
      })
    }

    return {
      setForm,
      setRules,
      ...toRefs(state),
      sendCode,
      setUserInfo
    }
  }
})
</script>

<style lang="less">
.setting-main {
  .el-card {
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.15) !important;
  }
}
</style>

<style lang="less" scoped>
.setting-container {
  background-color: #f8f8f8;
  min-height: 100vh;

  .setting-main {
    width: 700px;
    margin: 20px auto 0;
  }
}
</style>
