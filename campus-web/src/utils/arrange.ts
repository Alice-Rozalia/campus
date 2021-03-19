const arrFormat = (arr: any) => {
  const num = Math.ceil(arr.length / 4)

  let result = arr.map((item: any, index: number, array: []) => {
    if (index < 4) {
      return array.slice(index * num, (index + 1) * num)
    } else {
      return false
    }
  })

  return JSON.stringify(result.filter((item: any) => item))
}

export default arrFormat
