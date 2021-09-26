import Vue from 'vue'
import App from './App.vue'
import router from './router'

// 导入 element-ui 库和样式表
import ElememtUi from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// 导入全局样式
import './assets/css/login.less'

Vue.use(ElememtUi)

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
