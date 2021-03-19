// 同时发送一部代码的次数
let ajaxTimes = 0

export const request = (params) => {
  ajaxTimes++
  // 显示加载中效果
  wx.showLoading({ title: '加载中', mask: true })

  // 公共url
  const baseUrl = "http://localhost:10086/api/v1"

  return new Promise((resolve, reject) => {
    wx.request({
      ...params,
      url: baseUrl + params.url,
      success: (result) => {
        resolve(result);
      },
      fail: (err) => {
        reject(err);
      },
      complete: () => {
        ajaxTimes--
        if (ajaxTimes === 0) {
          wx.hideLoading()
        }
      }
    });
  })
}