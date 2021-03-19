import { ElNotification } from 'element-plus'

const Notification = (message: string): any => {
  return ElNotification({
    showClose: true,
    message
  })
}

const successNotification = (message: string): any => {
  return ElNotification({
    showClose: true,
    message,
    type: 'success'
  })
}

const warningNotification = (message: string): any => {
  return ElNotification({
    showClose: true,
    message,
    type: 'warning'
  })
}

const infoNotification = (message: string): any => {
  return ElNotification({
    showClose: true,
    message,
    type: 'info'
  })
}

const errorNotification = (message: string): any => {
  return ElNotification({
    showClose: true,
    message,
    type: 'error'
  })
}

export {
  Notification,
  successNotification,
  warningNotification,
  infoNotification,
  errorNotification
}
