import React, { Component } from 'react'
import styles from '../styles/Header.module.css'
import { Avatar, Input } from 'antd'
import { UserOutlined } from '@ant-design/icons'

const { Search } = Input

class Header extends Component {
  render() {
    return (
      <header className={styles.header}>
        <div className={styles.container}>
          <img src="/logo.jpg" alt="logo" className={styles.logo}/>
          <div className={styles.search}>
            <Search placeholder="显卡RTX3060" enterButton/>
          </div>
          <div className={styles.headerRight}>
            <Avatar size="large" icon={<UserOutlined/>}/>
            <span className={styles.userName}>登录</span>
          </div>
        </div>
      </header>
    )
  }
}

export default Header
