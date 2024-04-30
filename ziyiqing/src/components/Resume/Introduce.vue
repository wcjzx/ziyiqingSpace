<template>
  <div class="introduce">
    <div class="text">
      <div>
        <h1>嗨，</h1>
        <h1>我叫姚宇航</h1>
        <h3>25届江苏大学毕业生</h3>
        <h3>物联网工程</h3>
        <el-button class="buto">一纸简历</el-button>
      </div>
      <div>
        <img
          src="@/assets/images/illustration/Batch-TV-Folio-Animation-illustration-Bechtel-removebg-preview.png"
          alt=""
        />
      </div>
    </div>
    <slideDownward />
  </div>
</template>

 <script setup lang="js">
import {computed} from 'vue'
import slideDownward from '@/components/Resume/SlideDownward.vue'
components:{
    slideDownward
}

 </script>
<style scoped lang="scss">
@import "@/common/common";
.introduce {
  .text {
    // background: $c2;
    height: 420px;
    div:nth-child(1) {
      float: left;
      h1:nth-child(1){
        // margin: 0;
        margin-top: 80px;
        font-size: 40px;
        margin-left:100px;
      }
      h1:nth-child(2){
        margin-left:100px;
        font-size: 40px;
      }
      h3{
        // margin-top: 40px;
        margin-left:100px
      }
      .buto{
        margin-top: 20px;
        margin-left:100px;
        height:50px;
        width: 150px;
        background: $c3;
        // color: aqua;
        border-radius:10px ;
      }
    }
    div:nth-child(2) {
      float: right;
      img {
        margin-top: 60px;
        width: 234 * 2.5px;
        height: 131 * 2.5px;
      }
    }
  }
}
</style>
