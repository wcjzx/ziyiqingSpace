<template>
    <div class="CategoryOfArticle">
        <div class="container">
            <div class="container-item" v-for="(item, index) in articleList" :key="index" >
                <div class="item-titleAndinfo">
                    <div class="titleAndBox">
                        <div class="title" @click="toArticle(item.aid)">{{ item.articlename }}</div>
                        <DropDownBox :ArticleId="item.aid" ></DropDownBox>

                    </div>

                    <div class="info">
                        <div class="createTime">{{ item.articlecreatetime }}</div>
                        <div class="watchCount">{{ item.dianzhan }}</div>
                        <div class="CollectCount">{{ item.shouchang }}</div>
                    </div>
                </div>
                <div class="article-content">
                    <div>
                        {{ item.articleintro }}
                    </div>

                </div>
            </div>
        </div>
        <div class="pages"> <el-pagination background layout="prev, pager, next" :total="100"
                @size-change="handleSizeChange" @current-change="handleCurrentChange" :pager-count="6"
                style="font-size: 28px;">
            </el-pagination></div>

    </div>
</template>

<script setup lang="js">
import DropDownBox from "@/components/FunctionalComponents/DropDownBox.vue";
// #region 外部文件引入
import { watch, reactive, ref, toRefs, onMounted, computed } from "vue";
import { useRoute } from 'vue-router';
// #endregion

// #region 分类信息
const route = useRoute();
const categoryId = computed(() => {
    return route.params.categoryId;
});
// #endregion

// #region 测试用数据
const article = ref('');
const maxLength = 150; // 设置你想要截断的字数  
const truncatedText = computed(() => {
    if (article.value.length <= maxLength) {
        return article.value;
    } else {
        return article.value.substring(0, maxLength - 1) + '…'; // 在截断处添加省略号  
    }
});
// #endregion
// #endregion

// #region 分页和文章数据
const currentPage = ref('')
const articleList = ref([]);
const pageInfo = ref({});
const handleSizeChange = ((newSize) => {
    pageInfo.value.pages = newSize; // 更新每页大小  
    //   console.log('新的每页大小:', newSize);  
    // 在这里你可以进行其他操作，如调用后端接口获取数据等  
})
const handleCurrentChange = ((newPage) => {
    currentPage.value = newPage; // 更新当前页数  
    //   console.log('当前页数:', newPage);  
    // 在这里你可以进行其他操作，如调用后端接口获取数据等  
})

//     })
// }
// #endregion

// #region 搜索
const searchQuery = ref('')
const computedSearchQuery = computed(() => {
    return route.query.searchQuery || ''; // 使用路由查询参数，或者默认为空字符串  
});
// #endregion

// #region 文章编程式导航
import { useRouter } from "vue-router";
const router = useRouter();
const toArticle = (aid) => {
    // 使用编程式导航跳转到文章详情页
    console.log(aid);
    router.push({
        path: '/EachArticle',
        query: { id: aid }
    });
};
// #endregion

// #region 发送请求（分类的文章）
import { getArticleByCategoryId } from "@/utils/request";
// 首次加载或刷新页面时，获取文章列表
getArticleByCategoryId(categoryId.value, computedSearchQuery.value, pageInfo.value.current, 6).then((result) => {
    pageInfo.value = result.data;
    articleList.value = pageInfo.value.records;
}).catch((err) => {
});
// 仅在categoryId首次有有效值时触发一次请求  
watch(categoryId, (newVal, oldVal) => {
    if (newVal) {
        getArticleByCategoryId(newVal, searchQuery.value, pageInfo.value.current, 6).then((result) => {
            pageInfo.value = result.data;
            articleList.value = pageInfo.value.records;
        }).catch((err) => {
        });;
    }
});
watch(currentPage, (newVal, oldVal) => {
    if (newVal) {

        getArticleByCategoryId(categoryId.value, computedSearchQuery.value, newVal, 6).then((result) => {
            pageInfo.value = result.data;
            articleList.value = pageInfo.value.records;
        }).catch((err) => {
        });;

    }
});
watch(computedSearchQuery, (newVal, oldVal) => {
    console.log(newVal);
    if (newVal) {

        getArticleByCategoryId(categoryId.value, newVal, currentPage.value, 6).then((result) => {
            pageInfo.value = result.data;
            articleList.value = pageInfo.value.records;
        }).catch((err) => {
        });
    }
});
// #endregion
</script>

<style scoped lang="scss">
.CategoryOfArticle {
    .container {
        margin-top: 55px;

        .container-item {
            width: 1300px;
            // height: 400px;
            background: #fffcfcbe;
            border-radius: 20px;
            margin-top: 20px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;

            // align-items: center;
            .item-titleAndinfo {
                width: 100%;
                height: 100px;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                align-items: center;

                .titleAndBox {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;

                    .title {
                        margin: 30px 0 20px 0;
                        color: rgb(97, 141, 79);
                        font-size: 36px;
                        letter-spacing: 2px;
                        flex-shrink: 1;
                    
                        /* 防止标题在空间不足时被压缩 */
                    }

                    .DropDownBox {
                        margin-left: auto;
                        /* 将下拉框推到最右边 */
                    }
                }

                .info {
                    display: flex;
                    justify-content: center;
                    align-items: center;

                    div {
                        font-size: 16px;
                        padding: 0 20px;
                        color: rgba(5, 5, 5, 0.555);
                        // 右边有边框
                        border-right: 2px solid #03030396;
                    }
                }
            }

            .article-content {
                margin: 40px 40px;

                div {
                    color: rgba(71, 88, 112);
                    font-size: 19px;
                    line-height: 35px;
                    letter-spacing: 1.5px;
                }
            }

        }


    }

    .pages {
        margin: 40px 0;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .el-pagination .el-pagination-item {
        color: red;
        /* 这将改变页码的颜色 */
    }
}
</style>
