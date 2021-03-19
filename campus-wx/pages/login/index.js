Page({
  data: {

  },

  onLoad: function (options) {

  },

  handleGetUserInfo(e) {
    const { userInfo } = e.detail

    wx.setStorageSync("userInfo", userInfo)
    wx.navigateBack({
      delta: 1
    })
  }
})