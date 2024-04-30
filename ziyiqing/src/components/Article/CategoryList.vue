<template>
<div class="categoryList">
    <div class="categorylist-title">分类</div>
    <div class="categorylist-content">
        <div class="categorylist-content-item" v-for="(item,index) in categoryList" :key="index" >
            <div class="categorylist-content-item-content">{{item.categoryname}}</div>
        </div>
    </div>
</div>
</template>

 <script setup lang="js">
import { ref, onMounted, computed, defineEmits, defineComponent } from "vue";
import { useStore } from "@/pinia/index.js";
const store = useStore();
const categoryList = ref(
    []
);
console.log(categoryList)
store.getArticleCategoryInfo().then(() => {  
        // 假设 getArticleCategoryInfo 返回一个 Promise，并在数据加载完成后更新 categoryList  
        categoryList.value = store.Category;  
      });  

 </script>
<style scoped lang="scss">
// @import '@/SCSS/components/Article/categorylist.scss'
@import 'src/common/common.scss';

.categoryList {
    @include cardStyle;
    width: 260px;
    height: 450px;
    display:flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    // background: #456471;
    // height: 300px;
    border-radius: 15px;
    padding-top: 10px;
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
        .categorylist-content-item-content
        {
            text-align: center;
            &:hover{
                color: #2e5bbb;
                font-size: larger;

            }
        }   
    }
}
</style>
