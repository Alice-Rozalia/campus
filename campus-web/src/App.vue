<template>
  <router-view/>
</template>

<style lang="less">
  .el-carousel__item--card {
    border-radius: 12px;
  }

  .el-carousel__button {
    width: 7px !important;
    height: 7px !important;
    border-radius: 50% !important;
    background-color: #666;
  }

  .el-carousel__indicator.is-active button {
    background-color: #00a4ff;
  }
</style>
