import { request } from "../../request/index.js"
import regeneratorRuntime from '../../lib/runtime/runtime'

Page({
  data: {
    goods: {}
  },
  goodsInfo: {},

  onLoad: function (options) {
    const { id } = options
    this.fetchGoodsDetail(id)
  },

  // 获取商品详情
  async fetchGoodsDetail(id) {
    const { data } = await request({ url: "/pub/goods/" + id })
    if (data.success) {
      this.goodsInfo = data.data.goods
      this.setData({
        goods: data.data.goods
      })
    }
  },

  // 点击轮播图放大预览
  async handlePrevewImage(e) {
    const urls = this.goodsInfo.images.map(v => v.url)
    const current = e.currentTarget.dataset.url
    wx.previewImage({
      urls,
      current
    })
  }
})