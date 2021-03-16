package org.kuro.campus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.kuro.campus.model.entity.Event;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.response.ResultCode;
import org.kuro.campus.service.FollowService;
import org.kuro.campus.utils.CurrentUser;
import org.kuro.campus.utils.CustomConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/3/9 21:24
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "系统管理模块", tags = "关注管理")
public class FollowController implements CustomConstant {

    @Autowired
    private FollowService followService;

    @RequiresPermissions({"follow:interest"})
    @PostMapping("/pri/follow/{goodsId}")
    @ApiOperation(value = "关注", notes = "关注某个实体")
    public Result follow(@PathVariable("goodsId") Integer goodsId) {
        User user = CurrentUser.getCurrentUser();
        followService.follow(user.getId(), ENTITY_TYPE_GOODS, goodsId);

        return Result.ok(ResultCode.FOLLOW_WITH_INTEREST);
    }

    @RequiresPermissions({"unfollow:interest"})
    @PostMapping("/pri/unfollow/{goodsId}")
    @ApiOperation(value = "取消关注", notes = "取消关注某个实体")
    public Result unfollow(@PathVariable("goodsId") Integer goodsId) {
        User user = CurrentUser.getCurrentUser();
        followService.unFollow(user.getId(), ENTITY_TYPE_GOODS, goodsId);

        return Result.ok(ResultCode.UNFOLLOW_WITH_INTEREST);
    }

    @RequiresPermissions({"follow:has"})
    @GetMapping("/pri/follow/has/{goodsId}")
    @ApiOperation(value = "是否关注", notes = "查询用户是否关注某实体")
    public Result hasFollowed(@PathVariable("goodsId") Integer goodsId) {
        User user = CurrentUser.getCurrentUser();
        // 是否已关注
        Boolean hasFollowed = false;
        if (user != null) {
            hasFollowed = followService.hasFollowed(user.getId(), ENTITY_TYPE_GOODS, goodsId);
        }

        return Result.ok().data("hasFollowed", hasFollowed);
    }

    @RequiresPermissions({"follow:list"})
    @GetMapping("/pri/follow/list")
    @ApiOperation(value = "关注列表", notes = "查询用户关注某实体的数量和列表")
    public Result findFollowedCount(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "8") Integer limit
    ) {
        User user = CurrentUser.getCurrentUser();

        // 关注数量
        Long followeeCount = followService.findFolloweeCount(user.getId(), ENTITY_TYPE_GOODS);

        // 关注商品
        List<Map<String, Object>> followees = followService.findFollowees(user.getId(), page, limit);

        Map<String, Object> map = new HashMap<>();
        map.put("followeeCount", followeeCount);
        map.put("followees", followees);

        return Result.ok().data(map);
    }
}
