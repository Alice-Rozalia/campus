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
  }]
}]

export default menuList
