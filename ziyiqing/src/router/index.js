import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'
import Homepage from '@/views/Homepage.vue'
import resume from '@/views/Resume.vue'
import CategoryOfArticle from '@/components/Article/CategoryOfArticle.vue'
import NavigationBar from '@/views/NavigationBar.vue'
import EachArticle from '@/views/EachArticle.vue'
import EditArticle from '@/views/EditArticle.vue'
import Article from '@/views/Article.vue'
import photography from '@/views/photography.vue'





const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/NavigationBar',
    name: 'NavigationBar',
    component: NavigationBar,
    children: 
      [
        {
          path: '/Homepage',
          name: 'Homepage',
          component: Homepage
        },
        {
          path: '/photography',
          name: 'photography',
          component: photography
        },
        {
          path: '/resume',
          name: 'resume',
          component: resume
        },
        {
          path: '/article',
          name: 'article',
          component: Article
          ,
          children: [
            {
              path: '/CategoryOfArticle:categoryId',
              name: 'CategoryOfArticle',
              component: CategoryOfArticle,
              props: true
            }
          ]
        }
      ]
    },
    {
      path: '/EachArticle',
      name: 'EachArticle',
      component:EachArticle
    },
    {
      path:'/EditArticle',
      name:'EditArticle',
      component:EditArticle,
      props: true
    }
]
const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router