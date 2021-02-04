import React from 'react'
import Link from 'next/link'
import Head from 'next/head'
import { Carousel, Tag } from 'antd'
import Header from '../components/Header'
import styles from '../styles/Index.module.css'
import { ArrowDownOutlined, CaretRightOutlined } from '@ant-design/icons'

const Home = () => {

  return (
    <>
      <Head>
        <meta charSet="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>机电二手交易网</title>
      </Head>
      <div className={styles.bodyContainer}>
        <Header/>

        <div className={styles.mainWarp}>
          <div className={styles.banner}>
            <div className={styles.menuContent}>
              <div className={styles.menuItem}>
                <span>数码产品</span>
                <CaretRightOutlined/>
              </div>
              <div className={styles.menuItem}>
                <span>书籍教材</span>
                <CaretRightOutlined/>
              </div>
            </div>
            <div className={styles.rightBox}>
              <div className={styles.swiper}>
                <Carousel autoplay>
                  <div className={styles.swiperItem}>1</div>
                  <div className={styles.swiperItem}>2</div>
                </Carousel>
              </div>
              <div className={styles.orderBox}>
                <div className={styles.tags}><Tag>所有二手商品</Tag></div>
                <div className={styles.orderItem}>
                  <div className={styles.activeOrder}><span>时间</span><ArrowDownOutlined/></div>
                  <div className={styles.orderItemPrice}>价格</div>
                  <div>浏览量</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default Home
