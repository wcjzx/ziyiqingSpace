
import { createApp } from 'vue'


import App from './App.vue'
import TDesign from 'tdesign-vue-next';
import 'tdesign-vue-next/es/style/index.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from './router'
import axios from 'axios'
import '@/assets/icons/font_4468992_pkrudyypulg/iconfont.css'
import { createPinia } from 'pinia'
import '@/assets/icons/font_4468992_pkrudyypulg/iconfont.js'

const app = createApp(App)
 
app.use(createPinia())
app.config.globalProperties.axios = axios
app.use(ElementPlus)
app.use(TDesign);
app.use(router)

app.mount('#app')
