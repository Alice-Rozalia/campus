import { request } from "../../request/index.js"
import regeneratorRuntime from '../../lib/runtime/runtime'

Page({
  data: {
    tabs: [
      {id: 0, value: "时间", isActive: true},
      {id: 1, value: "价钱", isActive: false},
      {id: 2, value: "浏览量", isActive: false}
    ],
    goodsList: []
  },
  // 查询参数
  QueryParams: {
    page: 1,
    limit: 8
  },
  // 总页数
  totalPage: 1,
  cid: 0,

  onLoad: function (options) {
    this.cid = options.cid
    if (options.cid) {
      this.fetchGoodsListByCid(this.cid)
    }
  },

  // 页面相关事件处理函数--监听用户下拉动作
  onPullDownRefresh: function () {

  },

  // 页面上拉触底事件的处理函数
  onReachBottom: function () {
    if (this.QueryParams.page >= this.totalPage) {
      wx.showToast({ title: '没有下一页数据了' })
    } else {
      this.QueryParams.page++
      this.fetchGoodsListByCid(this.cid)
    }
  },

  // 获取商品列表
  async fetchGoodsListByCid(cid) {
    const { data } = await request({url: "/pub/goods/index/" + cid})
    if (data.success) {
      const total = data.data.goods.total
      this.totalPage = Math.ceil(total / this.QueryParams.limit)
      this.setData({
        goodsList: [...this.data.goodsList, ...data.data.goods.items]
      })
    }
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