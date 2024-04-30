<template>
  <div>
    <router-view/>
  </div>
</template>

<script setup lang="ts">

</script>

<style lang="scss" modules>
@import '@/assets/font.css';
body{
    font-size: 16px;
    // font-family: YeZiGongChangYanShanTingXingKai;
    font-family: HYZhengYuan-65S;
    // font-family: 大宝桃桃体;
    margin: 0px;
    padding: 0px;
}
// 阿里字体图标设置
.icon, .iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -webkit-text-stroke-width: 0.2px;
  -moz-osx-font-smoothing: grayscale;
}
</style>
