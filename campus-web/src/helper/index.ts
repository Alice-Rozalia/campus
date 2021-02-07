export const onCreateRandomRange = (start: number, end: number) => {
  return Math.round(Math.random() * (end - start) + start)
}

export const calculate = (x: number, y: number) => x + y

export const square = (x: number) => Math.pow(x, 2)
