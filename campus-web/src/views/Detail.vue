<template>
  <div class="detail-container">
    <Header/>

    <div class="goods-info">
      <div class="left-images">
        <el-carousel trigger="click" height="350px">
          <el-carousel-item v-for="item in goods.images" :key="item.id">
            <img :src="item.url" alt="goods">
          </el-carousel-item>
        </el-carousel>
      </div>

      <div class="detail-info">
        <div class="user-avatar">
          <el-avatar size="medium" :src="goods.avatar"/>
          <i>{{ goods.username }}</i>
        </div>
        <h2 class="goods-title">{{ goods.name }}</h2>
        <p>{{ goods.introduce }}</p>
        <div class="price-row">
          <i class="el-icon-price-tag"></i>
          <div>
            <span class="goods-price">￥{{ goods.price }}</span>
            <span>原价{{ goods.original }}</span>
          </div>
        </div>
        <div>
          <i class="el-icon-time"></i>
          <span class="publish-time">{{ goods.createDate }}</span>
        </div>
        <div class="location">
          <i class="el-icon-location-outline"></i>
          <span class="addr">{{ goods.address }}</span>
        </div>
        <div>
          <i class="el-icon-collection-tag"></i>
          <span class="goods-category">{{ goods.category }}</span>
        </div>
        <div class="talk">
          <el-button type="primary" size="mini">私聊</el-button>
        </div>
      </div>
    </div>

    <div class="comment-container">
      <div class="my-reply" style="margin-bottom: 20px">
        <el-avatar :size="40" :src="loginUser.avatar"></el-avatar>
        <el-input v-model="input1" autocomplete="off" placeholder="输入评论..."
                  style="width: 880px;margin: 0 30px 0 10px;"/>
        <el-button @click="commentHandle(1)" type="primary">发表评论</el-button>
      </div>
      <div v-for="item in comments" :key="item.comment.id" class="author-title">
        <el-avatar class="header-img" :size="40" :src="item.user.avatar"></el-avatar>
        <div class="author-info">
          <span class="author-name">{{ item.user.username }}</span>
          <span class="author-time">{{ dateStr(item.comment.createTime) }}</span>
        </div>
        <div class="icon-btn">
          <span><i class="el-icon-chat-dot-round"></i> {{ item.replyCount }}</span>
          <i class="el-icon-star-off"></i> 2
        </div>
        <div class="talk-box">
          <p>
            <span class="reply">{{ item.comment.content }}</span>
          </p>
        </div>

        <div class="reply-box">
          <div v-for="reply in item.replies" :key="reply.reply.id" class="author-title">
            <el-avatar class="header-img" :size="40" :src="reply.user.avatar"></el-avatar>
            <div class="author-info">
              <span class="author-name">{{ reply.user.username }}</span>
              <span class="author-time">{{ dateStr(reply.reply.createTime) }}</span>
            </div>
            <div class="icon-btn">
              <span class="reply-text" @click="showReplyInput(reply.reply.id)">回复</span>
            </div>
            <div class="talk-box">
              <p>
                <span class="reply-user">回复 {{ reply.target.username }}: </span>
                <span class="reply">{{ reply.reply.content }}</span>
              </p>
            </div>
            <div class="my-comment-reply" v-show="flag === reply.reply.id">
              <el-avatar :size="40" :src="loginUser.avatar"></el-avatar>
              <el-input v-model="input2" autocomplete="off" :placeholder="'@ ' + reply.user.username"
                        style="width: 880px;margin: 0 30px 0 10px;"/>
              <el-button @click="commentHandle(2, item.comment.id, reply.user.id)" type="primary">回复</el-button>
            </div>
          </div>
        </div>

        <div class="first-reply">
          <el-avatar :size="40" :src="loginUser.avatar"></el-avatar>
          <el-input v-model="input3" autocomplete="off" :placeholder="'@ ' + item.user.username"
                    style="width: 880px;margin: 0 30px 0 10px;"/>
          <el-button @click="commentHandle(3, item.comment.id, item.user.id)" type="primary">回复</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs, onMounted, onBeforeMount } from 'vue'
import { fetchGoodsDetailByIdApi } from '@/api/goods'
import { useRoute, useRouter } from 'vue-router'
import { addCommentApi } from '@/api/comment'
import { fetchCommentsApi } from '@/api/comment'
import { warningNotification } from '@/utils/notification'
import { successMessage, errorMessage } from '@/utils/message'
import dateStr from '@/utils/format'
import Header from '@/components/Header.vue'

export default defineComponent({
  name: 'Detail',
  components: {
    Header
  },
  setup() {
    const route = useRoute()
    const router = useRouter()

    const state = reactive({
      goods: {},
      comments: [],
      flag: -1,
      input1: '',
      input2: '',
      input3: '',
      loginUser: '',
      query: {
        page: 1,
        limit: 5
      }
    })

    onBeforeMount(() => {
      const user = window.sessionStorage.getItem('user')
      if (user) {
        state.loginUser = JSON.parse(user)
      }
    })

    onMounted(() => {
      getGoodsDetail()
      getCommentList()
    })

    const getGoodsDetail = async () => {
      const id: number = Number(route.params.id)
      if (!id) {
        router.push('/')
      }
      const { data } = await fetchGoodsDetailByIdApi(id)
      if (data.success) {
        state.goods = data.data.goods
      }
    }

    const getCommentList = async () => {
      const id: number = Number(route.params.id)
      const { data } = await fetchCommentsApi(id, state.query)
      if (data.success) {
        state.comments = data.data.comment
      }
    }

    const showReplyInput = (id: number) => {
      state.flag = id
    }

    const commentHandle = (type: number, cid: number, uid: number) => {
      const token = window.sessionStorage.getItem('token')
      if (!token) return warningNotification('尚未登录，请先登录！')
      if (state.input1.trim() === '' && state.input2.trim() === '' && state.input3.trim() === '') return errorMessage('评论内容不能为空！')
      switch (type) {
        case 1:
          const id: number = Number(route.params.id)
          addCommentApi({ entityId: id, content: state.input1 }).then(res => {
            if (res.data.success) {
              getCommentList()
              successMessage('评论成功！')
            }
          })
          break
        case 2:
          let com = { entityType: 1, entityId: cid, targetId: uid, content: state.input2 }
          addCommentApi(com).then(res => {
            if (res.data.success) {
              getCommentList()
              successMessage('评论成功！')
            }
          })
          break
        case 3:
          let com3 = { entityType: 1, entityId: cid, targetId: uid, content: state.input3 }
          addCommentApi(com3).then(res => {
            if (res.data.success) {
              getCommentList()
              successMessage('评论成功！')
            }
          })
          break
      }
    }

    return {
      ...toRefs(state),
      showReplyInput,
      commentHandle,
      dateStr
    }
  }
})
</script>

<style lang="less" scoped>
@import "../styles/detail.less";
</style>
