<template>
  <div class="container">
    <Header/>

    <div class="release-goods-container">
      <el-card>
        <h1 class="release-title">发布商品</h1>

        <el-form ref="goodsFormRef" :rules="goodsRules" :model="goodsForm" label-width="80px">
          <el-form-item label="商品名" prop="name">
            <el-input v-model="goodsForm.name" clearable placeholder="商品的商品名"/>
          </el-form-item>

          <el-form-item label="简介" prop="introduce">
            <el-input v-model="goodsForm.introduce" :autosize="{ minRows: 2, maxRows: 8}" size="medium" type="textarea"
                      clearable
                      placeholder="商品的简介，显示在详情页"/>
          </el-form-item>

          <el-form-item label="标价" prop="price">
            <el-input v-model.number="goodsForm.price" type="number" style="width: 50%;" clearable
                      placeholder="数字（保留两位小数），想卖的价格"/>
          </el-form-item>

          <el-form-item label="原价">
            <el-input v-model.number="goodsForm.original" type="number" style="width: 50%;" clearable
                      placeholder="如忘记可填大概价格，让用户对性价比一目了然"/>
          </el-form-item>

          <el-form-item label="分类" prop="categoryId">
            <el-cascader
              v-model="goodsForm.categoryId"
              :options="categories"
              :props="props"
              placeholder="合适的分类让买家能更好地找到您的商品"
              style="width: 50%;"
              clearable
            />
          </el-form-item>

          <el-form-item label="图片">
            <el-alert
              title="单张图片上传不能＞2M，全部图片（多张/单张）的总大小不能＞10M"
              type="info"
              show-icon
              :closable="false"
            >
            </el-alert>
            <el-upload
              action="#"
              list-type="picture-card"
              :auto-upload="false">
              <template #default>
                <i class="el-icon-plus"></i>
              </template>
              <template #file="{file}">
                <div>
                  <img class="el-upload-list__item-thumbnail" src="file.url" alt=""/>
                  <span>
                    <span>
                      <i class="el-icon-zoom-in"></i>
                    </span>
                    <span v-if="!disabled">
                      <i class="el-icon-download"></i>
                    </span>
                    <span v-if="!disabled">
                      <i class="el-icon-delete"></i>
                    </span>
                  </span>
                </div>
              </template>
            </el-upload>
          </el-form-item>

          <el-form-item label="">
            <el-button @click="publish('goodsFormRef')" type="primary" style="width: 100%;">提交</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs, getCurrentInstance, onBeforeMount } from 'vue'
import { fetchCategoriesApi } from '@/api/category'
import { goodsForm, goodsRules } from '@/utils/goodsValidators'
import { successMessage } from '@/utils/message'
import { publishGoodsApi } from '@/api/goods'
import { useRouter } from 'vue-router'
import Header from '@/components/Header.vue'

export default defineComponent({
  name: 'Release',
  components: {
    Header
  },
  setup() {
    let vm: any
    const router = useRouter()

    const state = reactive({
      categories: [],
      props: {
        expandTrigger: 'hover',
        value: 'id',
        label: 'name'
      }
    })

    const getCategories = async () => {
      const cacheCategories = window.sessionStorage.getItem('categories')
      if (cacheCategories) {
        state.categories = JSON.parse(cacheCategories)
      } else {
        const { data } = await fetchCategoriesApi()
        if (data.success) {
          state.categories = data.data.categories
          window.sessionStorage.setItem('categories', JSON.stringify(data.data.categories))
        }
      }
    }

    onMounted(() => {
      getCategories()
    })

    // 获取组件实例
    onBeforeMount(() => {
      vm = getCurrentInstance()
    })

    const publish = (formName: string): void => {
      vm.refs[formName].validate(async (valid: boolean) => {
        if (!valid) return
        const { data } = await publishGoodsApi(goodsForm)
        if (data.success) {
          router.push('/')
          successMessage(data.message)
        }
      })
    }

    return {
      ...toRefs(state),
      goodsForm,
      goodsRules,
      publish
    }
  }
})
</script>

<style lang="less" scoped>
  @import "../styles/release.less";
</style>

<style lang="less">
  .release-goods-container {
    .el-card {
      box-shadow: 0 1px 2px rgba(0, 0, 0, 0.15) !important;
    }
  }
</style>
