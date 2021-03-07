const dateStr = (date: string) => {
  let time: number = new Date().getTime()
  time = parseInt(String((time - new Date(date).getTime()) / 1000))

  let s

  if (time < 60 * 10) { // 十分钟内
    return '刚刚'
  } else if ((time < 60 * 60) && (time >= 60 * 10)) {
    // 超过十分钟少于1小时
    s = Math.floor(time / 60)
    return s + '分钟前'
  } else if ((time < 60 * 60 * 24) && (time >= 60 * 60)) {
    // 超过1小时少于24小时
    s = Math.floor(time / 60 / 60)
    return s + '小时前'
  } else if ((time < 60 * 60 * 24 * 30) && (time >= 60 * 60 * 24)) {
    // 超过1天少于30天内
    s = Math.floor(time / 60 / 60 / 24)
    return s + '天前'
  } else {
    // 超过 30天 ddd
    let d = new Date(parseInt(date))
    return d.getFullYear() + '/' + (d.getMonth() + 1) + '/' + d.getDate()
  }
}

export default dateStr
