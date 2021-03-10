package org.kuro.campus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.kuro.campus.event.EventProducer;
import org.kuro.campus.model.entity.Comment;
import org.kuro.campus.model.entity.Event;
import org.kuro.campus.model.entity.Goods;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.page.PageResult;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.response.ResultCode;
import org.kuro.campus.service.CommentService;
import org.kuro.campus.service.GoodsService;
import org.kuro.campus.service.LikeService;
import org.kuro.campus.service.UserService;
import org.kuro.campus.utils.CurrentUser;
import org.kuro.campus.utils.CustomConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/28 11:15
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "系统管理模块", tags = "评论管理")
public class CommentController implements CustomConstant {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private EventProducer eventProducer;

    @Autowired
    private GoodsService goodsService;

    @RequiresPermissions({"comment:add"})
    @PostMapping("/pri/comment/{goodsId}")
    @ApiOperation(value = "添加评论", notes = "添加评论")
    public Result addComment(
            @RequestBody @Valid Comment comment,
            @PathVariable("goodsId") Integer goodsId
    ) {
        commentService.addComment(comment);

        // 触发评论事件
        Event event = new Event()
                .setTopic(TOPIC_COMMENT)
                .setUserId(CurrentUser.getCurrentUser().getId())
                .setEntityType(comment.getEntityType())
                .setEntityId(comment.getEntityId())
                .setData("goodsId", goodsId);

        if (comment.getEntityType() == 0) {
            Goods target = goodsService.findGoodsById(comment.getEntityId());
            event.setEntityUserId(target.getUserId());
        } else if (comment.getEntityType() == 1) {
            Comment target = commentService.findCommentById(comment.getEntityId());
            event.setEntityUserId(target.getUserId());
        }

        eventProducer.fireEvent(event);

        return Result.ok(ResultCode.ADD_SUCCESS);
    }

    @GetMapping("/pub/comment/{goodsId}")
    @ApiOperation(value = "评论列表", notes = "根据商品id获取评论")
    public Result getCommentList(
            @PathVariable("goodsId") Integer goodsId,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "5") Integer limit
    ) {
        List<Comment> comment = commentService.findCommentByEntity(0, goodsId, page, limit);
        Integer total = commentService.findCountByEntity(0, goodsId);

        List<Map<String, Object>> commentVoList = new ArrayList<>();
        if (commentVoList != null) {
            for (Comment item : comment) {
                Map<String, Object> commentVo = new HashMap<>();
                // 评论
                commentVo.put("comment", item);
                // 评论人
                commentVo.put("user", userService.findUserById(item.getUserId()));
                // 点赞数量
                Long likeCount = likeService.findEntityLikeCount(1, item.getId());
                // 点赞状态
                Integer likeStatus = CurrentUser.getCurrentUser() == null ? 0 : likeService.findEntityLikeStatus(CurrentUser.getCurrentUser().getId(), 1, item.getId());

                commentVo.put("likeCount", likeCount);
                commentVo.put("likeStatus", likeStatus);

                // 回复列表
                List<Comment> replyList = commentService.findCommentByEntity(1, item.getId(), 1, Integer.MAX_VALUE);
                List<Map<String, Object>> replyVoList = new ArrayList<>();
                if (replyList != null) {
                    for (Comment reply : replyList) {
                        Map<String, Object> replyVo = new HashMap<>();
                        // 回复
                        replyVo.put("reply", reply);
                        // 回复人
                        replyVo.put("user", userService.findUserById(reply.getUserId()));
                        // 回复的目标
                        User target = reply.getTargetId() == 0 ? null : userService.findUserById(reply.getTargetId());
                        replyVo.put("target", target);
                        replyVoList.add(replyVo);
                    }
                }
                commentVo.put("replies", replyVoList);
                // 回复数量
                Integer replyCount = commentService.findCountByEntity(1, item.getId());
                commentVo.put("replyCount", replyCount);
                commentVoList.add(commentVo);
            }
        }

        PageResult<Map<String, Object>> result = new PageResult<>(total, commentVoList);

        return Result.ok().data("comment", result);
    }
}
