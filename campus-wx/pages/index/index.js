import { request } from "../../request/index.js"
import regeneratorRuntime from '../../lib/runtime/runtime'

Page({

  // 页面的初始数据
  data: {
    goodsList: []
  },

  // 生命周期函数--监听页面加载
  onLoad: function (options) {
    this.fetchGoodsList()
  },

  // 获取商品列表
  async fetchGoodsList() {
    const { data } = await request({url: "/pub/goods/index?page=1&limit=24"})
    if (data.success) {
      this.setData({
        goodsList: data.data.goods.items
      })
    }
  }
})