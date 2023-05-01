import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import request from "@/utils/request";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// Vue.use(Vuex)
Vue.use(ElementUI,{size:'small'})
// Vue.use(ElementUI)
import './assets/gloable.css'

Vue.prototype.request=request
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
