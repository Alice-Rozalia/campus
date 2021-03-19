# 校园二手交易网

[线上地址](http://39.105.91.14)

### 后端：

* Spring Boot + Shiro + MySQL + Redis

### 前端:

* Vue3.0 + TypeScript + element-plus + axios + 微信小程序

### 项目功能：

* 用户登录、用户注册、发表评论、点赞、私信、修改个人信息
* 关注or取消关注商品、查询自己关注or发布的商品、下架商品
* 搜索商品（根据分类、关键字搜索）、商品排序（时间、价格）
* 记录用户登录日志（含ip，城市，登录时间）

### 启动：

* 导入数据文件

* 修改`application.yml`中MySQL和Redis的配置

* 如果需要使用图片上传和短信服务，需要在`application.yml`中配置

    ```yml
    alioss:
      endpoint: # 你自己的accessKeyId
      accessKeyId: # 你自己的AccessKeySecret
      accessKeySecret: # 签名名称
      bucketName: # 模板名称

    campus:
      sms:
        accessKeyId:  # 你自己的accessKeyId
        accessKeySecret:  # 你自己的AccessKeySecret
        signName:  # 签名名称
        verifyCodeTemplate:  # 模板名称
    ```

项目预览图：

![](https://kuro-blog.oss-cn-guangzhou.aliyuncs.com/2020/12/01/1.jpg)

![](https://kuro-blog.oss-cn-guangzhou.aliyuncs.com/2020/12/01/2.jpg)

![](https://kuro-blog.oss-cn-guangzhou.aliyuncs.com/2020/12/01/3.jpg)