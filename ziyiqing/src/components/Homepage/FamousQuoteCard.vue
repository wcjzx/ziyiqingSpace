<template>
  <div class="famousQuoteCard">
    <div class="laster">
      <i class="icon iconfont icon-riqi" style="font-size: 36px; margin-right: 10px;" ></i>
      <span>「 快报 」</span>
    </div>
    <h4>
- {{NoticeLaster}}
      <i>-- {{NoticeLasterTime}}</i>
    </h4>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { getLatestNotice } from '@/utils/request';
const NoticeLaster = ref("子驿卿网站空间2024年4月1日调整布局中,期待成品！！");
const NoticeLasterTime = ref("2024-04-01");
getLatestNotice().then(res => {
  NoticeLaster.value = res.data.notice;
  let time=res.data.publictime.slice(0,10);
  NoticeLasterTime.value = time;
  console.log(res.data.notice);
})
</script>

<style scoped lang="scss">
@import "../../common/common.scss";

.famousQuoteCard{
  // 虚线边框
  border: 1px dashed #000000;
  border-radius: 10px;
  margin-left: 10px;
  // background: #337753;
  width: 1200px;
  .laster{
    margin: 30px;
    font-size: 20px;
  }
  h4{
    font-size: larger;
    margin-left:30px ;
    // 取消加粗
    font-weight: normal;
    color:#0000009f;
    // 字体倾斜


    i{
      float: right;
      margin-right: 50px;
    }
  }
}

</style>
