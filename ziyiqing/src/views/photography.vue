<template>
  <div class="photography">


    <div class="photography-list">
      <div class="updownphoto">
        <div class="addCover">
          <label for="file">
            <div class="file-button">上传图片</div>
            <input id="file" type="file" @change="onFileChange" />
          </label>
        </div>
      </div>
      <div class="photography-item" v-for="item in photography" :key="item.id">
        <div class="photography-time">
          <div class="photography-item-info-title">--------- {{ item.photographyCreatetime }} --------</div>
          <!-- 如果需要显示日期，可以取消下面这行的注释 -->
          <!-- <div class="photography-item-info-date">{{ item.date }}</div> -->
        </div>
        <div class="photography-item-content">
          <div v-for="photo in item.photographyWithTime" :key="photo.id" class="content">
            <img :src="baseUrl + photo.url" alt="" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="js">
import { ref, onMounted, computed, defineEmits, defineComponent } from "vue";
// #region 下载图片
const baseUrl = 'http://localhost:8090/photography/PhotographyDownload?name='

// #endregion
// #region 获取时间

const now = ref(new Date());
const currentDate = computed(() => {
  const year = now.value.getFullYear();
  const month = (now.value.getMonth() + 1).toString().padStart(2, '0');
  const date = now.value.getDate().toString().padStart(2, '0');
  return `${year}-${month}-${date}`;
});
// setInterval(() => {  
//   now.value = new Date();  
// }, 1000);
// #endregion
// #region 图片信息
const photography = ref([])
getphotography()
  .then(data => {
    photography.value = data;
  })
  .catch(error => {

  });
// #endregion
// #region 上传图片
import { postUploadphotography, getphotography } from '@/utils/request'
const selectedFile = ref()
const file = ref(null);
// const formData = new FormData();
const onFileChange = (event) => {
  now.value = new Date();
  console.log(currentDate.value)
  // 获取用户选择的文件列表  
  const files = event.target.files;
  if (files && files.length > 0) {
    // 因为我们只需要一个文件，所以直接取第一个文件  
    selectedFile.value = files[0]; // 将文件对象赋值给 ref
    // 定义一个文件对象
    file.value = selectedFile.value;
    const formData = new FormData();
    formData.append('file', file.value);
    formData.append('currentDate', currentDate.value.toString());
    console.log(currentDate.value.toString())
    postUploadphotography(formData);
    // 上传到服务器并回显
  } else {
    selectedFile.value = null; // 如果没有文件，将 ref 设置为 null  
  }
};
// #endregion


</script>
<style scoped lang="scss">
.photography {

  // width: 1920px;
  .photography-list {
    display: flex;
    flex-direction: column;

    justify-content: space-between;
    // align-items: center;
    margin: 0px 350px;

    .updownphoto {
      position: relative;
      margin: 40px 0 0 20px;
      display: flex;
      justify-content: center;
      width: 100px;
      height: 100px;
      background: #2124f7b6;
      margin-right: 50px;
      color: #fff;
      border-radius: 35px;
      width: 120px;
      padding: 0 15px;
      font-size: 20px;
      height: 60px;
      line-height: 60px;
     
input{
  position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            opacity: 0;
            cursor: pointer;
}
      // background: #000;
    }

    .photography-item {
      width: 1000px;
      display: flex;
      flex-direction: column;
    }

    .photography-time {
      text-align: center;
      /* 样式根据需要填写，例如： */
      padding: 10px;
      // background-color: #f5f5f5;
    }

    .photography-item-content {
      display: flex;
      flex-wrap: wrap;
      justify-content: start;
      /* 图片从容器顶部开始排列 */
      // align-items: ;
      /* 内容在垂直方向从顶部开始排列 */
    }

    .content {
      margin: 0 2px 4px 2px;

      img {
        // display: block;
        width: 320px;
        /* 图片宽度占满.content的宽度 */
      }
    }
  }
}
</style>
3