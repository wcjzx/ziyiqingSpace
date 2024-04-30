import {common,Article,Notice,user,category,photography} from './api'
// 向后端发送请求

// #region common模块 
export async function addCover(FormData){  
    const response = await common.post('/upload',FormData, {  
         headers: {  
             'Content-Type': 'multipart/form-data'  
           } 
     });
     const Articlepotho = response.data; 
     return Articlepotho;
 }
export async function getDownloadFile(name) {  
  try {
        const response = await common.get(`/download`, {
            params: {
                name: name
            },
            // responseType: 'blob' // 指示axios以Blob形式接收响应数据 
            responseType: 'blob' 
        });
        // 创建一个包含Blob的URL 
        return response.data; 
    } catch (error) {
        console.error('Error downloading file:', error);
    }  
}
// #endregion

// #region user 模块
export function login (username, password) {
    // 向后端发送登录请求
    return user.post('/login', {
        username,
        password
    })
}
export function getUserInfo () {
    // 从服务端获取用户信息
    return user.get('/userinfo')
}
// #endregion

// #region 文章模块
// #region 查询
export function getArticleCategoryInfo(){
    // 从'/category'获取文章分类信息
    return Article.get('/category')
}
export function getArticleNotice(){
    return Article.get('/notice')
}
export function getCategoryArticle(){
    return Article.get('/allArticle')
}
export function getNewArticle(){
    return Article.get('/newArticle')
}
export  function getArticleById(aid){
    return Article.get('/getArticleById',{params:{ aid:aid }})
}
export function getArticleByCategoryId(categoryId,searchQuery,currentPage,pageSize){
    return Article.get('/getArticleByCategoryId',{params:{ categoryId:categoryId,searchQuery,currentPage,pageSize }})
}
// #endregion
// #region 更新
export function updateArticle(articleData){  
    return Article.put('/updateArticle', 
        articleData
    );  
}
export function updateLikes(aid,likeCount){  
    return Article.put('/updateLikes', {  
        aid,likeCount
    });  
}
export function updateCollect(aid,collectCount){  
    return Article.put('/updateCollect', {  
        aid,collectCount
    });  
}
// #endregion
// #region 添加
export async function addArtcileId(){  
    const response = await Article.get('/addArtcileId')
    // 假设response.data包含了后端返回的int值  
    const newArticleId = response.data; 
    console.log(newArticleId); // 提取int值  
    return newArticleId
}
export async function postAddArticle(articleData) {  
    try {  
      const response = await Article.post('/add', articleData);  
      return response.data;  
    } catch (error) {  
      throw error; // 重新抛出错误以便在调用处处理  
    }  
}

// #endregion

// #region 删
export function deleteArticleById(aid){  
    return Article.delete('/deleteArticleById', {  
        params: {  
            aid: aid  
        }  
    });  
}
// #endregion
// #endregion

// #region 分类模块
// 增
export function getAddCategory(Cname){
    return category.post('/add', {Cname})
}
// #region 查询
export function getCategoryByName(Cname){
    return category.get('/CategoryByName',{Cname})
}
// #endregion
// #endregion

// #region 摄影模块
export function postUploadphotography(FormData){ 
    const response =  photography.post('/uploadphotography',FormData, {  
    headers: {  
        'Content-Type': 'multipart/form-data'  
      } 
});
const photographyData = response.data; 
return photographyData;
}
export function getphotography() {  
    return photography.get('/getPhotographyByTime') // 发起请求并返回Promise  
        .then(response => {  
            const photographyData = response.data;  
            // 可以在这里进行一些额外的处理，如果需要的话  
            return photographyData; // 返回数据  
        });  
}
// #endregion
// 通知请求
// 1. 最新通知
export function getLatestNotice(){
    return Notice.get('/latest')
}
