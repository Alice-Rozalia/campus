import { request } from "../../request/index.js"
import regeneratorRuntime from '../../lib/runtime/runtime'

Page({

  data: {
    goods: [],
    // 控制按钮显示
    isFocus: false,
    inpValue: ""
  },
  TimeId: -1,

  // 输入框的值改变就会触发
  handleInput(e) {
    const { value } = e.detail
    if (!value.trim()) {
      return
    }

    // 显示取消按钮
    this.setData({
      isFocus: true
    })

    clearTimeout(this.TimeId)
    this.TimeId = setTimeout(() => {
      this.fetchGoodsByKey(value)
    }, 1000)
  },

  // 搜索商品
  async fetchGoodsByKey(query) {
    const { data } = await request({url: "/pub/goods/index?key=" + query})
    if (data.success) {
      this.setData({
        goods: data.data.goods.items
      })
    }
  },

  // 点击取消按钮
  handleCancel() {
    this.setData({
      inpValue: "",
      isFocus: false,
      goods: []
    })
  }
})