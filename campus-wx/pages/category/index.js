import { request } from "../../request/index.js"
import regeneratorRuntime from '../../lib/runtime/runtime'

Page({
  // 页面的初始数据
  data: {
    leftMenuList: [],
    rightContent: [],
    // 被点击的左侧菜单
    currentIndex: 0
  },
  Cates: [],

  // 生命周期函数--监听页面加载
  onLoad: function (options) {
    // 在发送请求之前先判断本地存储中是否有旧数据
    const Cates = wx.getStorageSync("cates")
    if (!Cates) {
      this.fetchCates()
    } else {
      if (Date.now() - Cates.time > 1000 * 120) {
        this.fetchCates()
      } else {
        this.Cates = Cates.data
        // 左侧父分类数据
        let leftMenuList = this.Cates.map(v => v.name)
        // 右侧子分类数据
        let rightContent = this.Cates[0].children
        this.setData({
          leftMenuList,
          rightContent
        })
      }
    }
  },
  
  // 获取分类数据
  async fetchCates() {
    const { data } = await request({url: "/pub/category"})
    if (data.success) {
      this.Cates = data.data.categories
      // 把数据存储到本地存储
      wx.setStorageSync("cates", { time: Date.now(), data: this.Cates })

      // 左侧父分类数据
      let leftMenuList = this.Cates.map(v => v.name)
      // 右侧子分类数据
      let rightContent = this.Cates[0].children
      this.setData({
        leftMenuList,
        rightContent
      })
    }
  },

  // 左侧菜单的点击事件
  handleItemTap(e) {
    const { index } = e.currentTarget.dataset
    let rightContent = this.Cates[index].children
    this.setData({
      currentIndex: index,
      rightContent
    })
  }
})