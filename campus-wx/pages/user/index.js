Page({

  data: {
    userinfo: {}
  },

  onShow: function () {
    const userinfo = wx.getStorageSync("userInfo")
    this.setData({
      userinfo
    })
  }
})