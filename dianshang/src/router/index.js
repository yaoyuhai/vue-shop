import Vue from 'vue'
import VueRouter from 'vue-router'

// 导入要匹配路由的组件
import Login from '@/views/Login/login.vue'
import Home from '@/views/Home/home.vue'
// 用户管理
import User from '@/components/User/user.vue'
// 权限管理
import Rolelist from '@/components/Authority/rolelist.vue'
import Authoritylist from '@/components/Authority/authoritylist.vue'
// 商品管理
import Commoditylist from '@/components/Commodity/commoditylist.vue'
import ClassificationParameters from '@/components/Commodity/classification _parameters.vue'
import Categories from '@/components/Commodity/categories.vue'
// 订单管理和数据统计
import Orderlist from '@/components/Order/orderlist.vue'
import Datalist from '@/components/Data/datalist.vue'

Vue.use(VueRouter)

const routes = [
  // 匹配路由规则
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  {
    path: '/home',
    component: Home,
    children: [
      { path: 'user', component: User },
      { path: 'rolelist', component: Rolelist },
      { path: 'authoritylist', component: Authoritylist },
      { path: 'commoditylist', component: Commoditylist },
      { path: 'classification_parameters', component: ClassificationParameters },
      { path: 'categories', component: Categories },
      { path: 'orderlist', component: Orderlist },
      { path: 'datalist', component: Datalist }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

// 定义全局导航卫士
router.beforeEach((to, from, next) => {
  if (to.path === '/home') {
    const token = localStorage.getItem('token')
    if (token) {
      next()
    } else {
      next('/login')
    }
  } else {
    next()
  }
})

export default router
