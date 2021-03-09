<template>
  <div class="message-container">
    <Header/>

    <div class="message-main">
      <el-tabs type="card">
        <el-tab-pane>
          <template #label>
            <el-badge v-if="letterUnreadCount !== 0" :value="letterUnreadCount" :max="99" type="danger"
                      style="font-size: 12px">朋友私信
            </el-badge>
            <span v-else style="font-size: 12px">朋友私信</span>
          </template>
          <ul class="letter-list">
            <li v-for="item in letterList" :key="item.conversation.id">
              <el-badge v-if="item.unreadCount !== 0" :value="item.unreadCount" :max="99" type="danger"
                        style="font-size: 12px">
                <el-avatar :size="42" :src="item.target.avatar"></el-avatar>
              </el-badge>
              <el-avatar v-else :size="42" :src="item.target.avatar"></el-avatar>
              <div class="list-right" @click="goLetterDetailPage(item.conversation.conversationId)">
                <div class="list-right-hd">
                  <span>{{ item.target.username }}</span>
                  <span>{{ item.conversation.createTime }}</span>
                </div>
                <div class="list-right-bd">
                  <p>{{ item.conversation.content }}</p>
                  <span>共 {{ item.letterCount }} 条会话</span>
                </div>
              </div>
            </li>
          </ul>
        </el-tab-pane>
        <el-tab-pane>
          <template #label>
            <!--            <el-badge :value="0" :max="99" type="danger" style="font-size: 12px">系统通知</el-badge>-->
            <span style="font-size: 12px">系统通知</span>
          </template>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onBeforeMount, onMounted, reactive, toRefs } from 'vue'
import { useRouter } from 'vue-router'
import { warningNotification } from '@/utils/notification'
import { fetchLettersApi } from '@/api/message'
import Header from '@/components/Header.vue'

export default defineComponent({
  name: 'Message',
  components: {
    Header
  },
  setup() {
    const router = useRouter()

    const state = reactive({
      query: {
        page: 1,
        limit: 8
      },
      letterUnreadCount: 0,
      letterTotal: 0,
      letterList: []
    })

    onBeforeMount(() => {
      const token = window.sessionStorage.getItem('token')
      if (!token) {
        warningNotification('尚未登录，请先登录！')
        router.push('/')
        return
      }
    })

    onMounted(() => {
      getLetterList()
    })

    // 获取会话列表
    const getLetterList = async () => {
      const { data } = await fetchLettersApi(state.query)
      if (data.success) {
        state.letterUnreadCount = data.data.letterUnreadCount
        state.letterTotal = data.data.conversations.total
        state.letterList = data.data.conversations.items
      }
    }

    // 跳转会话详情页
    const goLetterDetailPage = (id: string) => {
      router.push('/letter/' + id)
    }

    return {
      ...toRefs(state),
      goLetterDetailPage
    }
  }
})
</script>

<style lang="less">
.message-main {
  .el-badge__content.is-fixed {
    top: 7px;
    right: 6px;
  }

  .el-tabs__item {
    padding: 0 30px;
  }
}
</style>

<style lang="less" scoped>
@import "../styles/message.less";
</style>
