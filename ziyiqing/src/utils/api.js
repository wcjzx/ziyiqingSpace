import axios from 'axios'
// 使用axios向后台发送请求
const service =axios.create({
    baseURL: 'http://localhost:8090',
})
const user =axios.create({
    baseURL: 'http://localhost:8090/user',
})
const Article =axios.create({
    baseURL: 'http://localhost:8090/article',
})
const Notice =axios.create({
    baseURL: 'http://localhost:8090/notice',

})
const common =axios.create({
    baseURL: 'http://localhost:8090/common',
})
const category =axios.create({
    baseURL: 'http://localhost:8090/category',

})
const photography =axios.create({
    baseURL: 'http://localhost:8090/photography',

})

export  {
    Article,
    service,
    Notice,
    user,
    common,
    category,
    photography
}