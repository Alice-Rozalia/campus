import { request } from "../../request/index.js"
import regeneratorRuntime from '../../lib/runtime/runtime'

Page({
  data: {
    tabs: [
      {id: 0, value: "时间", isActive: true},
      {id: 1, value: "价钱", isActive: false},
      {id: 2, value: "浏览量", isActive: false}
    ]
  },

  onLoad: function (options) {
    console.log(options);
  },

  // 页面相关事件处理函数--监听用户下拉动作
  onPullDownRefresh: function () {

  },

  // 页面上拉触底事件的处理函数
  onReachBottom: function () {

  },

  // 获取商品列表
  fetchGoodsList() {

  },

  // 标题的点击事件
  handleTabsItemChange(e) {
    // 获取被点击的标题索引
    const { index } = e.detail
    let { tabs } = this.data
    tabs.forEach((v, i) => i === index ? v.isActive = true : v.isActive = false)
    this.setData({
      tabs
    })
  }
})