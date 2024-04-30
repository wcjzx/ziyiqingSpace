<template>
  <div class="eachArticle">
    <div class="nagetive"  :style="{ backgroundImage: `url(${imageUrl})` }"
    >
      <div class="nagetive-top">
        <div class="n-t-left">
          <div class="return">
            <i class="icon iconfont icon-fanhui" style="margin: 0 30px; font-size: 30px" @click="returnTolast"></i>
          </div>
          <div class="name" style="margin-left: 20px; font-size: 25px">
            子驿卿
          </div>
        </div>
        <div class="n-t-right">
          <div class="search">
            <i class="icon iconfont icon-sousuo" style="margin-right: 60px; font-size: 30px"></i>
          </div>
        </div>
      </div>
      
      <div class="nagetive-bottom" v-if="article.articlephoto">
        <div class="title">
          {{ article.articlename }}
        </div>
        <div class="title2">

          <div class="watchcount" style="line-height: 20px">
            <i class="icon iconfont icon-guankan" style="margin-right: 10px; font-size: 20px"></i>
            {{ article.dianzhan }}
          </div>
          <div class="time" style="">
            <i class="icon iconfont icon-shijian1" style="margin-right: 10px; font-size: 20px"></i>
            {{ article.articlecreatetime }}
          </div>
        </div>
      </div>
      <div class="title-without-photo" v-else>  
    {{ article.articlename }}  
</div>
    </div>
    <div class="content">
      <div class="Article">
        <div class="MdPreview">
          <MdPreview :editorId="id" :modelValue="article.articlecontent" />
        </div>
        <!-- <MdCatalog :editorId="id" :scrollElement="scrollElement" />
        <div class="MdCatalog"> -->
        <!-- </div> -->
      </div>
      <div class="like-collect">
        <div class="like">
          <i class="icon iconfont icon-dianzan" style="font-size: 25px"></i>
          <div>
            {{ article.dianzhan }}
          </div>
        </div>
        <div class="collect">
          <i class="icon iconfont icon-shoucang" style="font-size: 30px"></i>
          <div></div>
          <div>
            {{ article.shouchang }}
          </div>
        </div>
      </div>
    </div>

    <div>  
  </div>  
  </div>
</template>

<script setup lang="js">
import { ref, watch, onMounted,computed } from 'vue';


// #region markdown插件
import { MdPreview, MdCatalog } from 'md-editor-v3';
import 'md-editor-v3/lib/preview.css';

// #endregion

// #region 下载图片


// #endregion

// #region 文章信息
const article = ref({});
// #endregion

// #region 路由跳转后接收信息和向后端请求数据
import { useRoute } from 'vue-router';
const route = useRoute();
import { getDownloadFile, getArticleById } from "@/utils/request";
import { common} from "@/utils/api";

const imageUrl = computed(() => {  
  return 'http://localhost:8090/common/download?name=' + article.value.articlephoto ;  
},);
const articleId = ref(null);
  articleId.value = route.query.id;
  const aid = articleId.value
  getArticleById(aid).then((result) => {
    article.value = result.data;
    downloadImage(article.value.articlephoto);
  }).catch((err) => {
  });
  // getDownloadFile(article.value.articlecover).then((result) => {

  // });
// 拼接两个字符串

  // #region 测试

  const downloadImage =  (name) => {  
    try {  
      const response =  common.get(`/download?name=${name}`, {  
        responseType: 'blob', // 告诉axios我们期望的响应数据类型是blob  
      });  
      console.log(response);
      const url = URL.createObjectURL(new Blob([response.data]));
      console.log(url);  
      imageUrl.value = url;  
    } catch (error) {  
      console.error('Error downloading image:', error);  
    }  
  };  
  // 假设在页面加载时就要下载图片  
 
// #endregion


// #endregion

 

// #region 返回按键函数
const returnTolast = () => {
  window.history.go(-1);
};
// #endregion

</script>
<style scoped lang="scss">
.markdown-body {
  box-sizing: border-box;
  margin: 0 auto;
  padding: 0 40px;
}

.eachArticle {
  width: 100%;
  height: 100%;

  .nagetive {
    // height: 380px;
    // 控制图片地位置
    background-size:cover;
    background-position: center;
    // background: #db1e1e;
    // #region flex布局
    display: flex;
    flex-direction: column;
    justify-content: start;
    align-items: center;

    // #endregion
    .nagetive-top {
      // #region flex布局
      display: flex;
      justify-content: space-between;
      align-items: center;
      // #endregion
      width: 100%;
      height: 70px;
    
        background-color: rgba(190, 196, 196, 0.329);
      

      .n-t-left {
        margin: 30px;
        display: flex;
        justify-content: center;
        align-items: center;

        .return {
          // 右边边框虚线
          border-right: 1px dashed #1817178f;
        }
      }
    }
.title-without-photo{
  margin: 10px 0 0 0;
  font-size: 40px;
  letter-spacing: 5px;
}
    .nagetive-bottom {
      height: 300PX;
      //  垂直方向处于父组件的中间
 
      width: 100%;
      display: flex;
      flex-direction: column;
      // justify-content: center;
      align-items: center;
      .title {
        // 给文字加黑
        opacity: 1;
        margin-top: 100px;
        font-size: 50px;
        color: #ffffff;
      }

      .title2 {
        display: flex;
        flex-direction: row;
        justify-content: center;
        height: 20px;
        color: #ffffff;

        div {
          margin-right: 10px;
        }
      }
    }
  }

  .content {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    margin: 0 400px;

    .Article {
      margin-top: 10px;
      display: flex;
      justify-content: center;
      align-items: center;

      .MdPreview {

      }
    }
    .like-collect {
      display: flex;
      // justify-content: flex-start;
      align-items: center;
      font-size: 25px;
      padding: 30px 0px;

      .like {
        width: 120px;
        height: 40px;
        margin-left: 100px;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 20px;
        background: #f05e5e;
      }

      .collect {
        margin-left: 50px;
        width: 120px;
        height: 40px;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 20px;
        background: #f05e5e;
      }
    }
  }
}
</style>
