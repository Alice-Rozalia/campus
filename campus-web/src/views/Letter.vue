<template>
  <div class="letter-container">
    <Header/>

    <div class="letter-main">
      <!-- 头部 -->
      <div class="letter-target">
        <el-avatar :size="40" :src="target.avatar"/>
        <el-divider direction="vertical"/>
        与
        <el-tag class="target-user" effect="plain">{{ target.username }}</el-tag>
        的私信
      </div>

      <!-- 会话列表 -->
      <div class="letter-list">
        <el-card v-for="item in letter" :key="item.letter.id" class="letter-card-item">
          <div class="letter-card-item-box" v-if="loginUser.id !== item.fromUser.id">
            <el-avatar :size="40" :src="item.fromUser.avatar"/>
            <el-divider direction="vertical"/>
            <span>
              <p class="letter-time">{{item.letter.createTime}}</p>
              <p>{{ item.letter.content }}</p>
            </span>
          </div>
          <div class="letter-card-item-box my-letter" v-else>
            <span>
              <p class="letter-time">{{item.letter.createTime}}</p>
              <p style="text-align: right">{{ item.letter.content }}</p>
            </span>
            <el-divider direction="vertical"/>
            <el-avatar :size="40" :src="item.fromUser.avatar"/>
          </div>
        </el-card>
      </div>

      <div class="send-letter">
        <el-input placeholder="发送私信..." v-model="content"/>
        <el-button type="primary" @click="sendLetter">发送私信</el-button>
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
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs, onBeforeMount } from 'vue'
import { fetchLetterDetailApi, sendLetterApi } from '@/api/message'
import { useRoute, useRouter } from 'vue-router'
import { warningNotification } from '@/utils/notification'
import { successMessage } from '@/utils/message'
import Header from '@/components/Header.vue'

export default defineComponent({
  name: "Letter",
  components: {
    Header
  },
  setup() {
    const route = useRoute()
    const router = useRouter()

    const id: string | string[] = route.params.id

    const state = reactive({
      letter: [],
      total: 0,
      target: {},
      loginUser: {},
      query: {
        page: 1,
        limit: 8
      },
      content: ''
    })

    onBeforeMount(() => {
      if (!id) return router.push('/')
    })

    onMounted(() => {
      const user: any = window.sessionStorage.getItem('user')
      state.loginUser = JSON.parse(user)
      getLetterList()
    })

    // 获取会话列表
    const getLetterList = async () => {
      const { data } = await fetchLetterDetailApi(id, state.query)
      if (data.success) {
        state.letter = data.data.letters.items
        state.total = data.data.letters.total
        state.target = data.data.target
      }
    }

    // 分页
    const handlePageChange = (page: number) => {
      state.query.page = page
      getLetterList()
    }

    // 发送私信
    const sendLetter = async () => {
      if (state.content.trim() === '') return warningNotification('私信内容不能为空！')
      const target = JSON.stringify(state.target)
      const loginUser = JSON.stringify(state.loginUser)
      const { data } = await sendLetterApi({
        fromId: JSON.parse(loginUser).id,
        toId: JSON.parse(target).id,
        content: state.content
      })

      if (data.success) {
        successMessage(data.message)
        getLetterList()
        state.content = ''
      }
    }

    return {
      ...toRefs(state),
      handlePageChange,
      sendLetter
    }
  }
})
</script>

<style lang="less" scoped>
@import "../styles/letter.less";
</style>
