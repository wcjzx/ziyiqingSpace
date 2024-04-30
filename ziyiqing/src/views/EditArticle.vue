<template>
  <div class="edit-article">
    <div class="nagetive"  :style="{ backgroundImage: imageUrlComputed }">
      <div class="nagetive-top" >
        <div class="n-t-left">
          <div class="return">
            <i
              class="icon iconfont icon-fanhui"
              style="margin: 0 30px; font-size: 30px"
              @click="returnTolast"
            ></i>
          </div>
          <div class="name" style="margin-left: 20px; font-size: 25px">
            子驿卿
          </div>
        </div>
        <div class="n-t-right">
          <div class="addCover">
            <label for="file">
              <div class="file-button">上传图片</div>
              <input id="file" type="file" @change="onFileChange" />
            </label>
          </div>
          <div class="search">
            <i
              class="icon iconfont icon-sousuo"
              style="margin-right: 60px; font-size: 30px"
            ></i>
          </div>
        </div>
      </div>
       <div class="nagetive-bottom"  v-if="imageUrlIsValid" >
        <input
          class="title"
          v-model="article.articlename"
          placeholder="文章标题"
          style="border: none"
        />

         <div class="title2">
           <div class="author">
            <i
              class="icon iconfont icon-fl-renyuan"
              style="margin-right: 10px; font-size: 20px"
            ></i
            >{{ article.author }}
          </div> 
           <div class="watchcount" style="line-height: 20px">
            <i
              class="icon iconfont icon-guankan"
              style="margin-right: 10px; font-size: 20px"
            ></i
            >{{ article.dianzhan }}
          </div>
          <div class="time" style="">
            <i
              class="icon iconfont icon-shijian1"
              style="margin-right: 10px; font-size: 20px"
            ></i>
            {{ article.articlecreatetime }}
          </div>
        </div>
       </div>
       <div class="title-without-photo" v-else>  
        <input
          class="title"
          v-model="article.articlename"
          placeholder="文章标题"
          style="border: none"
        />  
</div>
    </div>
    <div class="content">
      <div id="vditor" />
    </div>
    <div class="submit">
      <div class="submitBtn" v-if="!isUpdate" @click="publishArticle">发布</div>
    </div>
    <div class="submit">
      <div class="submitBtn" v-if="isUpdate" @click="publishArticleUpdate">修改</div>
    </div>
    <div v-if="showModal" class="modal">
      <div class="form-container">
        <div class="title">发布</div>
        <form @submit.prevent="submitForm">
          <div class="description-name">
            <label for="articleName">名称</label>
            <input
              type="text"
              id="articleName"
              v-model="article.articlename"
              :placeholder="article.articlename"
              required
            />
          </div>
          <div class="description-intro">
            <label for="articleDesc">简介</label>
            <textarea
              id="articleDesc"
              v-model="article.articleintro"
              required
            ></textarea>
          </div>
          <div class="description-cate">
            <label for="category">分类</label>
            <div class="booton">
              <select id="category" v-model="selectedCategory">
                <option
                  v-for="(category, index) in categories"
                  :key="index"
                  :value="category"
                >
                  {{ category.categoryname }}
                </option>
              </select>
              <input
                type="text"
                v-model="newCategory"
                placeholder="输入新分类"
              />
              <button type="button" @click="addCategory">新增分类</button>
            </div>
          </div>
          <div class="description-tap">
            <label for="tags">标签</label>
            <div class="tag-input">
              <div class="booton">
                <select id="tags" v-model="selectedtags">
                  <option
                    v-for="(tag, index) in tags"
                    :key="index"
                    :value="tag"
                  >
                    {{ tag }}
                  </option>
                </select>
                <input type="text" v-model="newTag" placeholder="输入新标签" />
                <button type="button" @click="addTag">新增标签</button>
              </div>
            </div>
          </div>
          <div class="submit">
            <button type="submit" @click="submitDetails">提交</button>
            <button @click="showModal = false">取消</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup lang="js">
// #region 页面文章信息
import { ref, onMounted, onUnmounted, watch,computed, defineEmits, defineComponent } from "vue";
import {getAddCategory} from '@/utils/request.js'
import { useRoute } from 'vue-router';
import { getArticleById,updateArticle } from '@/utils/request'
import { Article } from '@/utils/api'
import { postAddArticle,getCategoryByName,addCover } from '@/utils/request'
import {common,Notice,user} from '@/utils/api'
import Vditor from 'vditor';
import 'vditor/dist/index.css';
// #region 变量
const store = useStore();
const route = useRoute();
const router = useRouter();
const isUpdate = ref(false);
const isUpdatePhoto = ref(false);
const imageUrl = ref(''); 
const article = ref({
  aid: 1,
  articlename: "",
  articleintro: "",
  articlecontent:"",
  dianzhan: 0,
  shouchang: 0,
  articlecreatetime:'',
  articlephoto: "",
  cid:1,
});
const selectedCategory = ref('');
const currentTime = ref(new Date().toLocaleString());
const categories = ref([]);
const newCategory = ref("");
const vditorInstan = ref(null);
const selectedFile=ref();
const file =ref(null);
const showModal = ref(false);

article.value.articlecreatetime = currentTime.value;
// #endregion

// #region wacth函数
watch(selectedCategory, (newValue, oldValue) => {  
  if (newValue && newValue.cid) {  
    article.value.cid = newValue.cid;  
  }  
}, { immediate: false });

// #endregion

// #region 计算属性
const ArticleId = computed(() => {  
    return route.query.ArticleId || ''; // 使用路由查询参数，或者默认为空字符串  
  });

const imageUrlComputed = computed(() => {                                                                                                                                                                                                            
  if (imageUrl.value === '' || imageUrl.value === undefined) {  
    return null; // 如果imageUrl.value为null或undefined，返回null  
  } else if(isUpdatePhoto.value&&isUpdate.value) { 
    isUpdatePhoto.value=false;
    imageUrl.value= 'http://localhost:8090/common/download?name=' + article.value.articlephoto;
    return `url(${imageUrl.value})`;
  }
    else{  
      return `url(${imageUrl.value})`;
    }
});
// #endregion

// #region pinia分类数据
import { useStore } from "@/pinia/index.js";
store.getArticleCategoryInfo().then(() => {  
        // 假设 getArticleCategoryInfo 返回一个 Promise，并在数据加载完成后更新 categoryList  
        categories.value=store.Category; 
      });  
// #endregion

// #region 新增分类
const addCategory=()=> {
  if (newCategory.value !== "") {
    getAddCategory(newCategory.value).then((result) => {
      article.value.cid=result.data;
    }).catch((err) => {
    });
    newCategory.value = "";
  }
} 
// #endregion

// #region 编程式导航
import { useRouter } from "vue-router";
const returnTolast = () => {
  router.go(-1);
};
// #endregion

// #region 更新文章
  if(ArticleId.value!=''){  
    article.value.aid=ArticleId.value;
  getArticleById(ArticleId.value).then((res)=>{  
      console.log(res.data);
      article.value=res.data;
   imageUrl.value = 'http://localhost:8090/common/download?name=' + article.value.articlephoto;  
    if (vditorInstan.value) {  
    vditorInstan.value.setValue(article.value.articlecontent);  
  }  
  })
  .catch((err) => {  
    console.log(err);  
  });
   isUpdate.value=true;
   isUpdatePhoto.value=true;

  }
  const publishArticleUpdate=(()=>{
    article.value.articlecontent=vditorInstan.value.getValue()
    const formData = new FormData();
    formData.append('articleId', article.value.aid);
    formData.append('file', file.value);
    const response =  addCover(formData);
    article.value.articlephoto = response.data;
    updateArticle(article.value).then(()=>{
// 返回上一级目录
      router.go(-1);
    })
  })
// #endregion

// #region Vditor插件
onMounted(() => {
  vditorInstan.value = new Vditor('vditor', {
    height: '600px',
    input: '#vditor',
    cache: {
      enable: false,
    },
    hint: {
      markdown: {
        enable: true,
      }
    },
    after: () => {
      // vditor.value is a instance of Vditor now and thus can be safely used here
      // vditor.value!.setValue('Vue Composition API + Vditor + TypeScript Minimal Example');
    },
  });
});

// #endregion

// #region markdown文字内容
const submitDetails = () => {
  // 获取Vditor输入框中的Markdown内容  
        article.value.articlecontent=vditorInstan.value.getValue();
  // 将markdownContent转化为字符串 
  // 向后端发请求要Aid
  (async () => {  
      try {  
        const response = await Article.get('/addArtcileId');  
        const newArticleId = response.data;  
        article.value.aid = newArticleId; // 更新Article的Aid属性  
      } catch (error) {  
        console.error('Error fetching article ID:', error);  
        // 处理错误情况，比如显示一个错误消息等  
      }
      if(file.value!=null){
        const formData = new FormData();
    formData.append('articleId', article.value.aid);
    formData.append('file', file.value);
    const response = await addCover(formData);
    article.value.articlephoto = response.data;
      }
    
    try {
      const result = postAddArticle(article.value); // 调用request.js中定义的函数  
      console.log('提交成功', result);  
        showModal.value = false;
        router.go(-1);
        // 这里可以处理返回的数据，比如更新UI等  
      } catch (error) {  
        console.error('提交失败', error);  
        // 这里可以处理错误，比如显示错误消息给用户  
      }  
    })();
// 上传图片
};
// #endregion

// #region 添加封面
const onFileChange = (event) => {  
  // 获取用户选择的文件列表  
  const files = event.target.files;  
  if (files && files.length > 0) {  
    // 因为我们只需要一个文件，所以直接取第一个文件  
    selectedFile.value = files[0]; // 将文件对象赋值给 ref
    // 定义一个文件对象
    file.value = selectedFile.value;
    const url = URL.createObjectURL(selectedFile.value);  
    if(isFile(selectedFile.value)){
    imageUrl.value = url; 
    }
// 上传到服务器并回显
  } else {  
    selectedFile.value = null; // 如果没有文件，将 ref 设置为 null  
  }  
};
const isFile = (file) => {  
      if (file) {  
        const suffix = file.name.split('.')[1].toLowerCase(); // 转为小写进行比较  
        const sizeInMB = file.size / 1024 / 1024;  
        if (!['png', 'jpeg', 'jpg'].includes(suffix)) {  
          alert('上传图片只支持 PNG、JPEG、JPG 格式！');  
          return false;  
        }  
        if (sizeInMB > 2) {  
          alert('上传文件大小不能超过 2MB!');  
          return false;  
        }  
        return true;  
      }  
    }; 
    // 计算属性，将本地图片的Url拿到


/**
 * 清理函数，用于在组件卸载时释放资源。
 * 该函数没有参数，也没有返回值。
 * 主要作用是如果存在被选中的文件，就撤销其URL对象，以释放内存。
 */
const cleanup = () => {  
      if (selectedFile.value) {  
        URL.revokeObjectURL(imageUrl.value);  
      }  
    }; 
    onUnmounted(()=>{  
    cleanup();  
  }  )
// #endregion


// #region 模态框
const publishArticle=() => {
  showModal.value = true;
};
// #endregion

// #region 显示图片
const imageUrlIsValid = computed(() => {
  // 判断imageUrlComputed的值是否存在，如果不存在，则直接返回false
  if (!imageUrlComputed.value) {
    return false;
  }

  // 检查文章的图片字段是否为空，且图片URL是否以http开头
  // 如果文章图片为空且图片URL以http开头，则认为图片URL无效，返回false
  if (article.value.articlephoto === '' && imageUrl.value.startsWith('http')) {
    return false;
  }

  // 如果上述条件都不满足，则认为图片URL有效，返回true
  return true;
})
// #endregion
 
//  </script>
<style scoped lang="scss">
@import "@/common/common.scss";
.edit-article {
  .nagetive {
    background:  no-repeat center center / cover ;//复合式写法
    opacity: 1;
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
     
      &:hover{
        background-color: rgba(178, 184, 184, 0.76);
      }

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

      .n-t-right {
        display: flex;
        justify-content: center;
        align-items: center;

        .addCover {
          position: relative;
          display: flex;
          justify-content: center;
          align-items: center;
          margin-right: 40px;
          height: 50px;
          width: 150px;
          background: #070aaabe;
          border-radius: 25px;
          .file-button {
            color: #ffffff;
            // 每个文字间间隔
            letter-spacing: 2px;
            font-size: 20px;
          }
          input {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            opacity: 0;
            cursor: pointer;
          }
        }
      }
    }

    .nagetive-bottom {
      //  垂直方向处于父组件的中间
      height:350px;
      justify-content: center;
      justify-self: center;
      width: 100%;
      // margin-bottom: 60px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;

      .title {
        font-size: 50px;
        color: #ffffff;
        background-color: #92818100;
        outline: none;
        //width: 400px;
        width: 100%; /* 占据父容器的全部宽度 */  
        text-align: center; /* 文本居中 */ 
        &::placeholder {  
          color: rgb(255, 255, 255); /* 设置placeholder文本的颜色为红色 */  
          opacity: 0.9; /* Firefox需要这个属性来设置透明度 */  
        }  

      }
      .title2 {
        color: #ffffff;
        display: flex;
        flex-direction: row;
        justify-content: center;
        height: 20px;
        div {
          margin-right: 10px;
        }
      }
    }
    .title-without-photo{
      .title {
        font-size: 50px;
        color: #0f0f0f;
        outline: none;
        //width: 400px;
        width: 100%; /* 占据父容器的全部宽度 */  
        text-align: center; /* 文本居中 */ 
        &::placeholder {  
          color: rgba(0, 0, 0, 0.527); /* 设置placeholder文本的颜色为红色 */  
          opacity: 0.6; /* Firefox需要这个属性来设置透明度 */ 
          letter-spacing: 5px; 
        }  

      }
    }
  }

  .content {
    margin: 0 300px;

    .vditor {
      margin-top: 20px;
    }
  }

  .submit {
    display: flex;
    justify-content: center;

    .submitBtn {
      margin-top: 25px;
      margin-bottom: 25px;
      height: 60px;
      width: 150px;
      border-radius: 50px;
      background: #f80707ad;
      text-align: center;
      line-height: 60px;
      font-size: 25px;
      color: #f5f5f5;
    }
  }
  .modal {
    position: fixed;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    .form-container {
      display: flex;
      flex-direction: column;
      justify-content: start;
      align-items: center;
      background: #fff;
      width: 500px;
      height: 600px;
      border-radius: 30px;
      margin: 0 auto;
      padding: 20px;
      .title {
        text-align: center;
        font-size: 30px;
        //下边框虚线
        padding-top: 30px;
        padding-bottom: 20px;
        border-bottom: 1px dashed #1817178f;
      }
      form {
        //占完剩余空间
        flex: 1;
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        margin-bottom: 30px;
        .description-name {
          display: flex;
          flex-direction: column;
          justify-content: center;
          font-size: 20px;
          color: #000000a4;
          input {
            padding: 8px;
            margin-left: 10px;
            margin-top: 5px;
            border-radius: 10px;
            border: 1px solid #1f1f1f44;
            &:focus {
              outline: none;
            }
          }
        }
        .description-intro {
          display: flex;
          flex-direction: column;
          justify-content: center;
          font-size: 20px;
          color: #000000a4;
          textarea {
            padding: 8px;
            margin-left: 10px;
            margin-bottom: 5px;
            border-radius: 10px;
            border: 1px solid #1f1f1f44;
            &:focus {
              outline: none;
            }
          }
        }
        .description-cate {
          display: flex;
          flex-direction: column;
          justify-content: center;
          font-size: 20px;
          color: #000000a4;
          .booton {
            width: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-left: 10px;
            select {
              width: 60px;
              height: 35px;
              border: 1px solid #1f1f1f44;
              border-radius: 10px;
            }
            input {
              width: 150px;
              height: 35px;
              border: 1px solid #1f1f1f44;
              border-radius: 10px;
            }
          }
        }
        .description-tap {
          display: flex;
          flex-direction: column;
          justify-content: center;
          font-size: 20px;
          color: #000000a4;
          .booton {
            width: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-left: 10px;
            select {
              width: 60px;
              height: 35px;
              border: 1px solid #1f1f1f44;
              border-radius: 10px;
            }
            input {
              width: 150px;
              height: 35px;
              border: 1px solid #1f1f1f44;
              border-radius: 10px;
            }
          }
        }
        button[type="button"],
        button[type="submit"] {
          padding: 10px 15px;
          background-color: #4caf50;
          color: white;
          border: none;
          border-radius: 4px;
          cursor: pointer;
          margin-right: 10px;

          &:hover {
            background-color: #45a049;
          }
        }
      }

      // 响应式布局调整
      @media (max-width: 600px) {
        padding: 10px;
      }
    }
  }
}
</style>
