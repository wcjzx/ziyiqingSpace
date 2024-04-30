import { defineStore } from 'pinia'
import { getUserInfo } from '../utils/request'
import { getArticleCategoryInfo,getNewArticle } from '../utils/request'

export const useStore = defineStore('main', {
    state: () => {
        return {
            userInfo: {
                name: '',
                School:'',
                Professions:'',
                GraduateCity:''
            },
            Category: [],
            ArticleCategory: []
        }
    },
    actions: {
        // 异步 action，一般用来处理异步逻
        async getUserInfo() {
            const result = await getUserInfo()
            const data = result.data
            this.userInfo.name = data.username
            this.userInfo.School = data.school
            this.userInfo.Professions= data.professions
            this.userInfo.GraduateCity = data.graduatecity
        },
        async getArticleCategoryInfo(){
            const result = await getArticleCategoryInfo()
            const data = result.data
            this.Category = data
        },
        // 动态文章
        async getNewArticle(){
            const result = await getNewArticle()
            const data = result.data
            this.ArticleCategory = data
            console.log(this.ArticleCategory)
        }
    }
})