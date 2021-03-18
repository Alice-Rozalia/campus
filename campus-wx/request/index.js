export const request = (params) => {
  // 公共url
  const baseUrl = "http://localhost:8360/api/v1"

  return new Promise((resolve, reject) => {
    wx.request({
      ...params,
      url: baseUrl + params.url,
      success: (result) => {
        resolve(result);
      },
      fail: (err) => {
        reject(err);
      }
    });
  })
}