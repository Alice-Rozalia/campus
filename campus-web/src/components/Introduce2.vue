<template>
  <div class="introduce-swiper">
    <h1>校园二手交易网</h1>

    <div>
      <p class="title">项目功能：</p>
      <ul>
        <li>用户登录、用户注册、发表评论、点赞、私信、修改个人信息</li>
        <li>关注or取消关注商品、查询自己关注or发布的商品、下架商品</li>
        <li>搜索商品（根据分类、关键字搜索）、商品排序（时间、价格）</li>
        <li>记录用户登录日志（含ip，城市，登录时间）</li>
      </ul>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'Introduce2'
})
</script>

<style lang="less" scoped>
.introduce-swiper {
  width: 100%;
  height: 100%;
  background-color: #00a4ff;
  color: #ffffff;
  padding: 50px 100px;

  h1 {
    font-size: 24px;
    font-family: '华文行楷';
    padding-bottom: 15px;
  }

  .title {
    font-size: 16px;
    padding-bottom: 12px;
  }

  li {
    list-style: inside;
    padding: 8px 0;
    font-size: 15px;
  }
}
</style>
