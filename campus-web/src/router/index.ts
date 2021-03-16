import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Index',
    component: () => import('@/views/Index.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue')
  },
  {
    path: '/release_goods',
    name: 'Release',
    component: () => import('@/views/Release.vue'),
    meta: { requiredLogin: true }
  },
  {
    path: '/detail/:id',
    name: 'Detail',
    component: () => import('@/views/Detail.vue')
  },
  {
    path: '/message',
    name: 'Message',
    component: () => import('@/views/Message.vue'),
    meta: { requiredLogin: true }
  },
  {
    path: '/letter/:id',
    name: 'Letter',
    component: () => import('@/views/Letter.vue'),
    meta: { requiredLogin: true }
  },
  {
    path: '/my_center',
    name: 'MyCenter',
    component: () => import('@/views/MyCenter.vue'),
    meta: { requiredLogin: true }
  },
  {
    path: '/setting',
    name: 'Setting',
    component: () => import('@/views/Setting.vue'),
    meta: { requiredLogin: true }
  },
  {
    path: '/admin/login',
    name: 'AdminLogin',
    component: () => import('@/views/admin/Login.vue')
  },
  {
    path: '/admin',
    name: 'Admin',
    component: () => import('@/views/admin/Admin.vue'),
    redirect: '/admin/index',
    meta: { requiredLogin: true },
    children: [{
      path: '/admin/index',
      name: 'AdminIndex',
      component: () => import('@/views/admin/Index.vue')
    }]
  },
  {
    path: '/:catchAll(.*)',
    name: '/404',
    component: () => import('@/views/404.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const { requiredLogin } = to.meta
  const token = window.sessionStorage.getItem('token')
  if (!token && requiredLogin) {
    next('/login')
  } else {
    next()
  }
})

export default router
