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
          <el-button v-if="hasFollowed" type="info" size="mini" @click="unfollowInterest">已收藏</el-button>
          <el-button v-else type="danger" size="mini" @click="followInterest">收藏</el-button>
          <el-button @click="dialogVisible = true" type="primary" size="mini">私聊</el-button>
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
          <span @click="handleLike(item.comment.id, item.user.id)" :class="item.likeStatus === 1 ? 'liked' : ''">
            <i class="el-icon-star-off"></i> {{ item.likeCount }}
          </span>
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

      <div class="footer-pagination">
        <el-pagination
          layout="prev, pager, next"
          :total="total"
          :page-size="query.limit"
          @current-change="handlePageChange"
        />
      </div>
    </div>

    <el-dialog
      title="发送私信"
      v-model="dialogVisible"
      width="50%"
    >
      <el-input v-model="content" autocomplete="off" placeholder="发送私信..."/>
      <template #footer>
    <span class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="sendLetter">发 送</el-button>
    </span>
      </template>
    </el-dialog>
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
import { sendLetterApi } from '@/api/message'
import { giveLikeApi } from '@/api/like'
import { hasFollowApi, followInterestApi, unfollowInterestApi } from '@/api/follow'
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
      },
      dialogVisible: false,
      content: '',
      total: 0,
      hasFollowed: false
    })

    const id: number = Number(route.params.id)

    onBeforeMount(() => {
      if (!id) {
        router.push('/')
      }

      const user = window.sessionStorage.getItem('user')
      if (user) {
        state.loginUser = JSON.parse(user)
        hasFollow()
      }
    })

    onMounted(() => {
      getGoodsDetail()
      getCommentList()
    })

    // 获取商品详情
    const getGoodsDetail = async () => {
      const { data } = await fetchGoodsDetailByIdApi(id)
      if (data.success) {
        state.goods = data.data.goods
      }
    }

    // 查询用户是否收藏了该商品
    const hasFollow = async () => {
      const { data } = await hasFollowApi(id)
      if (data.success) {
        state.hasFollowed = data.data.hasFollowed
      }
    }

    // 获取评论列表
    const getCommentList = async () => {
      const { data } = await fetchCommentsApi(id, state.query)
      if (data.success) {
        state.comments = data.data.comment.items
        state.total = data.data.comment.total
      }
    }

    // 显示评论输入框
    const showReplyInput = (id: number) => {
      state.flag = id
    }

    // 评论
    const commentHandle = (type: number, cid: number, uid: number) => {
      const token = window.sessionStorage.getItem('token')
      if (!token) return warningNotification('尚未登录，请先登录！')
      if (state.input1.trim() === '' && state.input2.trim() === '' && state.input3.trim() === '') return errorMessage('评论内容不能为空！')
      switch (type) {
        case 1:
          addCommentApi({ entityType: 0, entityId: id, content: state.input1 }, id).then(res => {
            if (res.data.success) {
              getCommentList()
              successMessage('评论成功！')
            }
          })
          break
        case 2:
          let com = { entityType: 1, entityId: cid, targetId: uid, content: state.input2 }
          addCommentApi(com, id).then(res => {
            if (res.data.success) {
              getCommentList()
              successMessage('评论成功！')
            }
          })
          break
        case 3:
          let com3 = { entityType: 1, entityId: cid, targetId: uid, content: state.input3 }
          addCommentApi(com3, id).then(res => {
            if (res.data.success) {
              getCommentList()
              successMessage('评论成功！')
            }
          })
          break
      }
    }

    // 发送私信
    const sendLetter = async () => {
      if (state.content.trim() === '') return warningNotification('私信内容不能为空！')
      const target = JSON.stringify(state.goods)
      const loginUser = JSON.stringify(state.loginUser)

      const fromId = JSON.parse(loginUser).id
      const toId = JSON.parse(target).userId

      if (fromId === toId) return warningNotification('不能与自己私聊！')

      const { data } = await sendLetterApi({ fromId, toId, content: state.content })

      if (data.success) {
        successMessage(data.message)
        state.content = ''
        state.dialogVisible = false
      }
    }

    // 点赞
    const handleLike = async (entityId: number, userId: number) => {
      const token = window.sessionStorage.getItem('token')
      if (!token) return warningNotification('尚未登录，请先登录！')

      const { data } = await giveLikeApi({ entityUserId: userId, entityId: entityId, goodsId: id })
      if (data.success) {
        getCommentList()
      }
    }

    // 收藏商品
    const followInterest = async () => {
      const token = window.sessionStorage.getItem('token')
      if (!token) return warningNotification('尚未登录，请先登录！')

      const { data } = await followInterestApi(id)
      if (data.success) {
        successMessage('已收藏！')
        hasFollow()
      }
    }

    // 取消收藏商品
    const unfollowInterest = async () => {
      const { data } = await unfollowInterestApi(id)
      console.log(data)
      if (data.success) {
        successMessage('已取消收藏！')
        hasFollow()
      }
    }

    // 分页
    const handlePageChange = (page: number) => {
      state.query.page = page
      getCommentList()
    }

    return {
      ...toRefs(state),
      showReplyInput,
      commentHandle,
      dateStr,
      sendLetter,
      handleLike,
      handlePageChange,
      followInterest,
      unfollowInterest
    }
  }
})
</script>

<style lang="less" scoped>
@import "../styles/detail.less";
</style>
