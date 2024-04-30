<template>
  <div class="articleList">
    <div class="spatial-dynamics" v-for="(item, index) in spatialdynamics" :key="index">
      <div class="tab">
        <i class="icon iconfont icon-dongtai" style="font-size: 30px; margin-right: 10px"></i>
        <span>{{ item.categoryName }}</span>
      </div>
      <div class="spatial-dynamics-content">
        <!-- 如果flavors数组长度为0，则显示提示信息 -->
        <div v-if="item.flavors.length === 0" class="no-articles-tip">
          空空如也~
        </div>
        <div class="spatial-dynamics-content-item" v-for="(item, index) in item.flavors" :key="index">
          <div class="img-box" @click="toArticle(item.aid)">
            <!-- <div> {{ item.articlephoto }}</div> -->
            <div class="img" :style="{ backgroundImage: `url(${imageUrl}${item.articlephoto})` }"></div>
          </div>

          <!-- 文字区域 -->
          <div class="text-box">
            <!-- 标签、分类 -->
            <!--  标题 -->
            <div class="title" @click="toArticle(item.aid)">{{ item.articlename }}</div>
            <!-- 发表时间 -->
            <div class="time">
              <i class="icon iconfont icon-shijian" style="margin-right: 10px"></i>{{ item.articlecreatetime }}
            </div>
            <!-- 简介 -->
            <div class="item-introduction">{{ item.articleintro }}</div>
            <!-- 点赞、收藏、评论 -->
            <div class="item-like-collect-comment">
              <div>
                <i class="icon iconfont icon-dianzan"></i> {{ item.like }}
                {{ 11 }}
              </div>
              <div>
                <div>
                  <i class="icon iconfont icon-shoucang"></i> {{ item.collect }}
                  {{ 22 }}
                </div>
              </div>
              <div>
                <div>
                  <i class="icon iconfont icon-pinglun"></i> {{ item.comment }}
                  {{ 33 }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <el-pagination background layout="prev, pager, next" 
    :total="1000"
    @size-change="handleSizeChange"  
    @current-change="handleCurrentChange"  
    :page-sizes="[10, 20, 30, 40]"  
    :pager-count="6" >
    </el-pagination>
  </div>
</template>

<script setup lang="js">
import { ref, onMounted, computed, defineEmits, defineComponent } from "vue";
import { getCategoryArticle } from "@/utils/request"
const spatialdynamics = ref([]);
getCategoryArticle().then(res => {
  spatialdynamics.value = res.data
  
})
const imageUrl = ref('http://localhost:8090/common/download?name=');
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

// #region 分页信息
const PageInfo = ref({
  currentPage: 1,
  pageSize: 10
}); 
const handleSizeChange = (val) => {
  PageInfo.value.pageSize = val;
  console.log(PageInfo.value.pageSize);
};
const handleCurrentChange = (val) => {
  PageInfo.value.currentPage = val;
    console.log(PageInfo.value.currentPage);
};
// #endregion
</script>
<style scoped lang="scss">
// @import '@/SCSS/components/Article/ArticleList.scss'
@import "@/common/common";

.articleList {
  .spatial-dynamics {
    // background: #000000ad;
    width: 1200px;
    display: flex;
    justify-content: start;
    //  align-items: center;
    flex-direction: column;
    // flex-wrap: nowrap;
    // flex-flow: row wrap;

    .tab {
      width: 1100px;
      padding: 0 0 8px 30px;
      font-size: 20px;
      // 虚线下边框
      border-bottom: 1px dashed #ccc;
    }

    .spatial-dynamics-content {
      width: 100%;
      display: flex;
      flex-direction: column;
      margin-bottom: 20px;

      .spatial-dynamics-content-item {
        @include cardStyle;
        display: flex;
        height: 120px;
        margin-top: 10px;
        width: 1200px;

        .img-box {
          overflow: hidden;
          margin: 10px;
          border-radius: 50%;

          .img {
            height: 100px;
            width: 100px;
            /*--设置背景图显示区域高度--*/
            background: #000000b9;
            background-repeat: no-repeat;
            transform: scale(1.1); //原本的图片的大小，图片原来的大小不变
            transition: all 0.8s;
            background-size: cover;

            &:hover {
              transform: scale(1.2);
            }
          }

          //   .img {
          //   height: 170px;
          //   /* 设置背景图显示区域高度 */
          //   // border-radius: 10px;
          //   /* 设置背景图像 */
          //   background-repeat: no-repeat;
          //   background-position: center;
          //   /* 图片缩小适应框的大小 */
          //   transition: all 0.8s;
          // }

          // .img:hover {
          //   transform: scale(1.1);
          //   /* 鼠标悬停时背景图片覆盖整个容器 */
          // }
        }



        .text-box {
          display: flex;
          flex-direction: column;
          justify-content: space-around;
          height: 120px;

          .tag-box {
            display: flex;
            justify-content: left;
            flex-direction: row;
            // .tag {
            //   margin-left: 15px;
            //   margin-top: 10px;
            //   background: #cccccc81;
            //   line-height: 25px;
            //   border-radius: 5px;
            //   color: #000000ad;
            //   padding: 0 8px;
            //   font-size: 12px;
            // }
          }

          .title {
            margin-top: 10px;
            margin-left: 15px;
            font-size: 18px;
          }

          .time {
            margin-left: 15px;
            font-size: 13px;
            color: #02020283;
          }

          .item-introduction {
            display: -webkit-box;
            /*设置为弹性盒子*/
            -webkit-line-clamp: 2;
            /*最多显示5行*/
            overflow: hidden;
            /*超出隐藏*/
            text-overflow: ellipsis;
            /*超出显示为省略号*/
            -webkit-box-orient: vertical;
            word-break: break-all;
            /*强制英文单词自动换行*/
            margin-left: 15px;
            font-size: 14px;
            color: #000000d2;
          }

          .item-like-collect-comment {
            display: flex;
            justify-content: left;
            margin-bottom: 10px;
            // align-items: center;
            margin-left: 20px;
            margin-right: 80px;
            font-size: small;
            color: #000000b9;

            div {
              margin-right: 8px;
            }
          }
        }
      }
    }
  }

  .no-articles-tip {
    text-align: center;
    padding: 20px;
    /* 你可以添加更多的样式来美化这个提示信息 */
  }
}
</style>
