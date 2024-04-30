<template>
  <div class="listOfItems">
    <div class="tab">
      <i class="icon iconfont icon-dongtai" style="font-size: 30px; margin-right: 10px"></i>
      <span>最新动态</span>
    </div>
    <div class="spatial-dynamics">
      <!-- ref="dongtai" -->
      <div class="spatial-dynamics-content-item" v-for="(item, index) in spatialdynamics" :key="index" Aid="item.aid">
        <div class="img-box" @click="toArticle(item.aid)">
          <div class="img" :style="{ backgroundImage: `url(${imageUrl}${item.articlephoto})` }"></div>
        </div>
        <!-- 文字区域 -->
        <div class="text-box">
          <!-- 标签、分类 -->
          <div class="tag-box">
            <div class="tag" v-for="(tag, index) in item.tagName" :key="index">
              <i class="icon iconfont icon-biaoqian" style="margin: 10px 2px 0 0; font-size: small"></i>{{ tag }}
            </div>
          </div>
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
              <i class="icon iconfont icon-dianzan" :class="{ active: item.isLiked }" @click="toggleLike(item)"
                style="font-size: 25px"></i>{{ item.dianzhan }}
            </div>
            <div>
              <div>
                <i class="icon iconfont icon-shoucang" :class="{ active: item.isCollected }"
                  @click="toggleCollect(item)" style="font-size: 30px"></i>{{ item.shouchang }}
              </div>
            </div>
            <div>
              <div>
                <i class="icon iconfont icon-pinglun" style="font-size: 25px"></i>{{ item.comment }}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { getNewArticle } from "../../utils/request";
import { updateLikes, updateCollect } from "../../utils/request";
import "@/assets/icons/font_4468992_pkrudyypulg/iconfont.js";
import { useStore } from "@/pinia/index";
const store = useStore();

const imageUrl = ref('http://localhost:8090/common/download?name=');

// #region 获取文章数据
const spatialdynamics = ref([]);
store.getNewArticle()
  .then(() => {
    // 假设 res.data 是从后端获取的文章数组
    // 在SpatialDynamics变量中，添加自定义数据在每篇文章中
    const articlesWithCustomData = store.ArticleCategory.map((article) => {
      // 为每篇文章添加自定义数据
      return {
        ...article, // 保留原始文章数据
        // 添加自定义数据
        isLike: "fales",
        isCollecte: "fales", // 示例自定义数据
      };
      // 将数据存入pinia  
    });
    spatialdynamics.value = articlesWithCustomData;
  })
  .catch((error) => {
    console.error("Error fetching articles:", error);
  });

// #endregion

// #region 点赞、收藏、评论功能
const toggleLike = (item) => {
  // 切换点赞状态
  item.isLiked = !item.isLiked;
  // 根据新的点赞状态更新点赞数
  if (item.isLiked) {
    item.dianzhan++;
  } else {
    item.dianzhan--;
  }
  // 向后端发送请求，更新数据库中的点赞数
  let aid = item.aid;
  let likeCount = item.dianzhan;
  updateLikes(aid, likeCount)
    .then((res) => { })
    .catch((error) => {
      console.log(error);
    });
};
const toggleCollect = (item) => {
  // 切换点赞状态
  item.isCollected = !item.isCollected;
  // 根据新的点赞状态更新点赞数
  if (item.isCollected) {
    item.shouchang++;
  } else {
    item.shouchang--;
  }
  // 向后端发送请求，更新数据库中的点赞数
  let aid = item.aid;
  let collectCount = item.shouchang;
  updateCollect(aid, collectCount)
    .then((res) => { })
    .catch((error) => {
      console.log(error);
    });
};
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
</script>

<style scoped lang="scss">
// 生成组件有关的scss结构
@import "@/common/common";

.listOfItems {
  margin: 30px 0 auto;
  width: 1200px;

  .tab {
    padding: 0 0 8px 30px;
    font-size: 20px;
    // 虚线下边框

    border-bottom: 1px dashed #ccc;
  }

  .spatial-dynamics {
    display: flex;
    justify-content: start;
    //  align-items: center;
    flex-direction: row;
    flex-wrap: nowrap;
    flex-flow: row wrap;
    flex-grow: 1;
    flex-shrink: 1;
    flex-basis: auto;
    flex: 1 1 auto;

    .spatial-dynamics-content-item {
      @include cardStyle;

      // margin-right: 40px;
      height: 350px;
      margin: 20px 20px 0 20px;
      width: 350px;

      .img-box {
        overflow: hidden;
        border-radius: 10px 10px 0 0;
        .img {
          height: 170px;
          /* 设置背景图显示区域高度 */
          // border-radius: 10px;
          background-image: url('@/assets/images/15912486859130400.jpg');
          /* 设置背景图像 */
          background-repeat: no-repeat;
          background-position: center;
          background-size:cover;
          /* 图片缩小适应框的大小 */
          transition: all 0.8s;
        }

        .img:hover {
          transform: scale(1.1);
          /* 鼠标悬停时背景图片覆盖整个容器 */
        }
      }

      .text-box {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        height: 180px;

        .tag-box {
          display: flex;
          justify-content: left;
          flex-direction: row;

          .tag {
            margin-left: 15px;
            margin-top: 10px;
            background: #cccccc81;
            line-height: 25px;
            border-radius: 5px;
            color: #000000ad;
            padding: 0 8px;
            font-size: 12px;
          }
        }

        .title {
          margin-left: 15px;
          font-size: 20px;
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
          justify-content: space-around;
          margin-bottom: 10px;
          align-items: center;
          margin-left: 10px;
          margin-right: 180px;
          font-size: 18px;
          color: #000000b9;
          line-height: 18px;

          /* 假设你的图标是通过字体图标或SVG来实现的，这里以字体图标为例 */
          .icon {
            /* 初始样式，可能是透明的或者默认的黑色 */
            color: black;
          }

          .icon.active {
            /* 激活状态的样式，红色实心 */
            color: red;
          }
        }
      }
    }
  }
}
</style>