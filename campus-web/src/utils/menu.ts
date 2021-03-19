const menuList = [{
  id: '1',
  icon: 'el-icon-user',
  menuName: '工作台',
  children: [{
    id: '11',
    path: '/admin/index',
    menuName: '后台首页'
  }]
}, {
  id: '2',
  icon: 'el-icon-user',
  menuName: '用户管理',
  children: [{
    id: '21',
    path: '/admin/users',
    menuName: '用户列表'
  }, {
    id: '22',
    path: '/admin/login_log',
    menuName: '登录日志'
  }]
}, {
  id: '3',
  icon: 'el-icon-goods',
  menuName: '商品管理',
  children: [{
    id: '31',
    path: '/admin/goods',
    menuName: '商品列表'
  }]
}]

export default menuList
