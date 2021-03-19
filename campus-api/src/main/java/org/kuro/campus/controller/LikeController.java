package org.kuro.campus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.kuro.campus.model.entity.Event;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.service.LikeService;
import org.kuro.campus.utils.CurrentUser;
import org.kuro.campus.utils.CustomConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/3/9 14:16
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "系统管理模块", tags = "点赞管理")
public class LikeController implements CustomConstant {

    @Autowired
    private LikeService likeService;

    @RequiresPermissions({"give:like"})
    @PostMapping("/pri/give/like")
    @ApiOperation(value = "点赞", notes = "给实体点赞（此处只做评论点赞）")
    public Result like(
            @RequestParam(value = "entityId", required = true) Integer entityId,
            @RequestParam(value = "entityUserId", required = true) Integer entityUserId,
            @RequestParam(value = "goodsId", required = true) Integer goodsId
    ) {
        User user = CurrentUser.getCurrentUser();

        // 实现点赞
        likeService.like(user.getId(), ENTITY_TYPE_COMMENT, entityId);

        // 数量
        Long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_COMMENT, entityId);
        // 状态
        Integer likeStatus = likeService.findEntityLikeStatus(user.getId(), ENTITY_TYPE_COMMENT, entityId);

        Map<String, Object> map = new HashMap<>();
        map.put("likeCount", likeCount);
        map.put("likeStatus", likeStatus);

        return Result.ok().data(map).message("点赞成功！");
    }
}
