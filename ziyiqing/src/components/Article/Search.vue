<template>
  <div class="search">

    <div class="form">
      <input type="text" placeholder="搜索文章..." v-model="searchQuery" style="position: relative;" />
      <button type="submit" @click="search">
        <i class="icon iconfont icon-sousuo" style="margin-right: 60px; font-size: 30px;
              position: absolute;
              top: 30px;
              left: 40px;
              font-size: 40px;"></i>
      </button>
    </div>
  </div>
</template>

<script setup lang="js">
import { ref, defineEmits } from 'vue';
import axios from 'axios';

const searchQuery = ref('');
const emit = defineEmits(['searchQuery']);
const search = () => {
  emit('searchQuery', searchQuery.value);
};
</script>
<style scoped lang="scss">
.search {
  width: 1200px;

  .form {
    height: 100px;
    position: relative;
    width: 1200px;
    margin: 0 auto;

    input {
      font-size: 30px;
      width: 100%;
      height: 100%;
      padding-left: 50px;
      padding-right: 46px;
      border: none;
      outline: none;
      border-radius: 50px;
      border: 2px solid #324b4e;
      background: #7fccf054;
      // transition: 0.3s linear;

    }
  }

  button {
    height: 42px;
    width: 42px;
    cursor: pointer;
    position: absolute;
    border: none;
    outline: none;
    background: none;
    top: -2px;
    right: 0;
    // &:before {
    //   content: "\f002";
    //   font-family: FontAwesome;
    //   color: #324b4e;
    // }
  }
}
</style>
