<template>
  <div class="container">
    <div class="notification">
      <img src="https://kuro-blog.oss-cn-guangzhou.aliyuncs.com/summer-pockets/daohe.png"/>
      <el-alert
        title="404：该页面不存在！"
        type="error"
        :closable="false"
        effect="dark">
      </el-alert>

      <div class="index-page">
        <el-link href="/" type="success">返回首页</el-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { errorNotification } from '@/utils/notification'

export default defineComponent({
  name: '404',
  mounted(): void {
    errorNotification('该页面不存在！')
  }
})
</script>

<style lang="less" scoped>
  .container {
    width: 100vw;
    height: 100vh;
    background: #F5F5F5;
    background-size: cover;
    position: relative;

    .notification {
      position: fixed;
      width: 380px;
      height: 140px;
      background-color: #fff;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      border-radius: 5px;
      padding: 20px 0;

      > img {
        position: absolute;
        top: -177px;
        left: 46px;
        width: 200px;
      }

      .index-page {
        text-align: center;
        padding-top: 30px;
        font-size: 30px;
      }
    }
  }
</style>
