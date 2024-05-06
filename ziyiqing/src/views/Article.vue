<template>
  <div class="article">
    <!-- 搜索框 -->
    <search @searchQuery="handleSearchResults"></search>
    <div class="article-content">
      <div class="article-left">
        <!-- 分类组件 -->
        <div class="categoryList" :style="{ top: `${moduleTop}px` }">
          <div class="categorylist-title">分类</div>
          <div class="categorylist-content">
            <div class="categorylist-content-item" v-for="(item, index) in categoryList" :key="index">
              <router-link class="categorylist-content-item-content" active-class="active" :to="{
      name: 'CategoryOfArticle',
      params: { categoryId: item.cid }
    }">
                {{ item.categoryname }}                              
              </router-link>
            </div>
          </div>
        </div>
      </div>
      <div class="article-right">
        <!-- 文章列表组件 -->
        <div class="main-content">
          <RouterView></RouterView>
        </div>
        <!-- <articleList /> -->
      </div>
    </div>
  </div>
</template>
<script setup lang="js">
// import categoryList from "@/components/Article/CategoryList.vue";
import search from "@/components/Article/Search.vue";
import { ref, onMounted, onUnmounted, onBeforeUnmount, computed, defineEmits, defineComponent, onBeforeMount } from "vue";
import { useStore } from "@/pinia/index.js";
import { useRouter } from "vue-router";
const router = useRouter();

const store = useStore();
const categoryList = ref(
  []
);
const searchQuery = ref('1');
onBeforeMount(() => {
});
const moduleTop = ref(125)
const scrollThreshold = ref(126)// 分类模块顶部的位置  
const offset = 126;
store.getArticleCategoryInfo().then(() => {
  // 假设 getArticleCategoryInfo 返回一个 Promise，并在数据加载完成后更新 categoryList  
  categoryList.value = store.Category;
});
// #region 搜索功能


function handleSearchResults(results) {
  searchQuery.value = results;
  // console.log(searchQuery.value);
  router.push({
    name: 'CategoryOfArticle',
    params: { categoryId: -1} ,
    query: {  
    searchQuery: searchQuery.value,  
  }  },
    )
}
// #endregion
// #region 分类模块的悬停效果

const handleScroll = () => {
  const scrollTop = window.scrollY || document.documentElement.scrollTop;
  if (scrollTop > scrollThreshold) {
    // 设置分类模块的顶部位置为阈值加上额外的偏移量  
    moduleTop.value = scrollThreshold + offset;
  } else {
    // 否则，随着页面滚动而移动分类模块，并加上额外的偏移量  
    moduleTop.value = scrollTop + offset;
  }
};

// 在组件挂载后添加滚动事件监听器  
onMounted(() => {
  window.addEventListener('scroll', handleScroll);
});

// 在组件卸载前移除滚动事件监听器  
onBeforeUnmount(() => {
  window.removeEventListener('scroll', handleScroll);
});
// #endregion


</script>
<style scoped lang="scss">
@import 'src/common/common.scss';

.article {
  height: 100%;
  position: relative;

  &::before {
    height: 1600px;
    content: "";
    background: url('@/assets/images/BackgroundPicture/wallhaven-qz8z3d.jpg');
    opacity: 0.9;
    /* 调整这里的值以改变透明度，范围从0（完全透明）到1（完全不透明） */
    top: 0;
    left: 0;
    bottom: 0;
    right: 0px;
    position: absolute;
    z-index: -1;
    background-size: cover;
    //图片不随鼠标滚动
    background-attachment: fixed; // 图片不随鼠标滚动  
  }

  display: flex;
  flex-direction: column;
  align-items: center;

  .search {
    text-align: center;
    margin-top: 25px;
  }

  .article-content {
    padding: 0 100px;
    display: flex;

    .article-left {
      .categoryList {
        position: absolute;
        left: 100px;
        // top: 200px;
        @include cardStyle;
        margin-top: 96px;

        width: 260px;
        height: 450px;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
        // background: #456471;
        // height: 300px;
        border-radius: 15px;
        padding-top: 10px;
        background: #ffffffab;

        .categorylist-title {
          margin-top: 10px;
          font-size: 35px;
        }

        .categorylist-content {
          font-size: 20px;
          color: #000000ab;
          margin: 10px;
          height: 80%;
          display: flex;
          flex-direction: column;
          justify-content: space-around;

          .categorylist-content-item-content {
            // 取消下划线
            text-decoration: none;
            color: #000000d7;
            text-align: center;

            &:hover {
              color: #2e5bbb;
              font-size: larger;

            }
          }
        }
      }

      // .categoryList {
      //   margin-top: 96px;
      // }

    }

    .article-right {
      width: 1400px;
      height: 780px;

      // background: #FAE8E0;
      margin-left: 300px;
      // border-radius: 10%;
      margin-top: 40px
    }
  }
}
</style>
