<template>
  <div class="navigationBar">
    <div 
      class="navigation" :class="{ show: isShow }"
        :style="{ backgroundImage: `url(${backgroundImagePath})`}"
    >
      <!-- 导航区 -->
      <div class="box">
        <span class="name" 
        @click="ToLogin">{{ store.userInfo.name }}</span>
        <nav class="nav">
          <router-link class="link" active-class="active" to="/Homepage"
            >首页</router-link
          >
          <router-link class="link" active-class="active" to="/CategoryOfArticle1"
            >文章</router-link
          >
          <router-link class="link" active-class="active" to="/photography"
            >相册</router-link
          >
          <div class="EditArticle"
          @click="ToEditArcticle"
          >
            <div>
              <i class="icon iconfont icon-tianjia" style="font-size: 20px"></i>
              发布文章
            </div>
          </div>
        </nav>
      </div>
    </div>
    <!-- 展示区 -->
    <div class="main-content">
      <RouterView></RouterView>
    </div>
  </div>
</template>

 <script setup lang="js">
import { ref,reactive, onBeforeMount,onMounted, computed, watch,defineEmits, defineComponent } from "vue";
import {RouterView,RouterLink,useRouter} from 'vue-router'
import { useStore } from '@/pinia/index.js'
const backgroundImagePath = ref('');
const isShow = ref(true); 
// 从store获取后端的个人信息数据
const store = useStore();
const router = useRouter()

onBeforeMount(()=>{
store.getUserInfo();
})
// 首次即加载主页信息
// 根据路由路径设置背景图片
watch(  
  () => router.currentRoute.value.fullPath,  
  (newValue) => {  
    if (newValue.startsWith('/CategoryOfArticle')) { 
      isShow.value = false; 
      // 如果路由以 '/article' 开头，无论是否有子路径，都设置相同的背景图片  
      backgroundImagePath.value = 'src/assets/images/BackgroundPicture/wallhaven-qz8z3d.jpg';  
    } else {  
      // 处理其他路由的情况  
      switch (newValue) {  
        case '/Homepage':
        isShow.value = true;  
          backgroundImagePath.value = 'src/assets/images/BackgroundPicture/image.png';  
          break;  
        case '/photography':  
        isShow.value = true;
          backgroundImagePath.value = 'src/assets/images/BackgroundPicture/wallhaven-qz8z3d.jpg';  
          break;  
        case '/article':  
        isShow.value = false;
          backgroundImagePath.value = 'src/assets/images/BackgroundPicture/wallhaven-qz8z3d.jpg';  
          break;  
        default:  
          backgroundImagePath.value = '';  
      }  
    }  
  },  
  { immediate: true }  
);  
// #region 编程式路由
// import { useRouter } from 'vue-router'
// const router = useRouter();
const ToEditArcticle = ()=>{
  router.push({
    name:'EditArticle'
  })
}
const ToLogin = ()=>{
  router.push({
    name:'Home'
  })
}
// #endregion


 </script>
<style scoped lang="scss">
$f-size: 25px;
@mixin fontgs {
  margin-left: 70px;
  line-height: 70px;
  font-size: 25px;
}
.navigationBar {
  
  .show {  
    background-size: 100%;  
    border-radius: 0 0 5% 5%;  
    background-repeat: no-repeat;  
    height: 380px;  
  }  
  .navigation {
    // background-position: 0px -420px;

    .box {
      display: flex;
      justify-content: space-between;
      //  align-items: center;
      height: 90px;
      padding: 0px;

      &:hover {
        background-color: rgba(64, 76, 78, 0.4);
      }
      .name {
        @include fontgs();
        text-decoration: none;
        font-size: 30px;
        line-height: 70px;
        margin-top: 10px;

        color: #fff;
      }
      .nav {
        // float: right;
        display: flex;
        @include fontgs;
        margin-right: 10px;
        height: 70px;
        margin-top: 10px;

        .EditArticle {
          margin-right: 50px;
          color: #fff;
        
          div {
            background: #2124f7b6;
            border-radius: 35px;
            width: 120px;
            padding: 0 15px;
            font-size: 20px;
            height: 60px;
            line-height: 60px;
            margin: 5px 0;
            

          }
        }
        .link {
          text-decoration: none;
          line-height: 70px;
          font-size: 30px;
          margin-right: 40px;
          color: #fff;
        }
      }
    }
  }
}
</style>