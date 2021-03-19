<template>
  <div class="introduce-swiper">
    <h1>校园二手交易网</h1>
    <div class="warp">
      <div>后端：</div>
      <p>Spring Boot + Shiro + MySQL + Redis</p>
    </div>
    <div class="warp">
      <div>前端：</div>
      <p>Vue3.0 + TypeScript + element-plus + axios + 微信小程序</p>
    </div>
    <div class="warp">
      <div>Github：</div>
      <a class="link" href="https://github.com/Alice-Rozalia/campus">https://github.com/Alice-Rozalia/campus</a>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'Introduce'
})
</script>

<style lang="less" scoped>
.introduce-swiper {
  width: 100%;
  height: 100%;
  background-color: #31c27c;
  color: #ffffff;
  padding: 50px 100px;

  h1 {
    font-size: 24px;
    font-family: '华文行楷';
    padding-bottom: 15px;
  }

  p {
    height: 36px;
    line-height: 36px;
  }

  .warp {
    padding: 10px 0;
  }

  .link {
    display: block;
    height: 40px;
    line-height: 40px;
    color: #409eff;
  }
}
</style>
