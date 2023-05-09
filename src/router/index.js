import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";
// import Login from '../views/Login.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/about',
    name: 'About',
    component: () => import('../views/About.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/Register',
    name: 'register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/404',
    name: '404',
    component: () => import('../views/404.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//重置路由
export const resetRouter = () => {
  router.matcher = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes
  })
}


//设置动态路由
export const setRouters = () => {
  const storeMenus = localStorage.getItem("menus");
  if (storeMenus) {

    const currentPathNames = router.getRoutes().map(v => v.name)
    if (!currentPathNames.includes("Manage")) {
      const manageRoute = { path: '/', name: "Manage", component: () => import('../views/Manage.vue'), redirect: "/home", children: [] }
      const menus = JSON.parse(storeMenus)
      menus.forEach(x => {

        if (x.path) {
          let itemMenu = { path: x.path.replace("/", ""), name: x.name, component: () => import('../views/' + x.pagePath + '.vue') }
          manageRoute.children.push(itemMenu)
        } else if (x.children.length) {
          x.children.forEach(y => {
            let itemMenu = { path: y.path.replace("/", ""), name: y.name, component: () => import('../views/' + y.pagePath + '.vue') }
            manageRoute.children.push(itemMenu)
          })
        }

      });
      router.addRoute(manageRoute)
    }



  }
}
setRouters()


// 路由守卫
router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName", to.name)  // 设置当前的路由名称，为了在Header组件中去使用
  store.commit("setPath")  // 触发store的数据更新
  const storeMenu = localStorage.getItem("menus")
  if (!to.matched.length) {
    if (storeMenu) {
      next("/404")
    } else {
      next("/login")  // 放行路由
    }
  }

  next()
})

export default router