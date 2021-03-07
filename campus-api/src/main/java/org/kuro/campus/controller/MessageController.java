package org.kuro.campus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.kuro.campus.model.entity.Message;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.page.PageResult;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.response.ResultCode;
import org.kuro.campus.service.MessageService;
import org.kuro.campus.service.UserService;
import org.kuro.campus.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/3/3 11:54
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "系统管理模块", tags = "消息管理")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @RequiresPermissions({"message:list"})
    @GetMapping("/pri/message/letters")
    @ApiOperation(value = "私信列表", notes = "获取私信列表")
    public Result getLetterList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "8") Integer limit
    ) {
        User user = CurrentUser.getCurrentUser();
        // 会话列表
        List<Message> conversationList = messageService.findConversations(user.getId(), page, limit);
        Integer total = messageService.findConversationCount(user.getId());

        List<Map<String, Object>> conversations = new ArrayList<>();
        if (conversationList != null) {
            for (Message message : conversationList) {
                Map<String, Object> map = new HashMap<>();
                map.put("conversation", message);
                map.put("letterCount", messageService.findLetterCount(message.getConversationId()));
                map.put("unreadCount", messageService.findLetterUnreadCount(user.getId(), message.getConversationId()));
                Integer targetId = user.getId() == message.getFromId() ? message.getToId() : message.getFromId();
                map.put("target", userService.findUserById(targetId));

                conversations.add(map);
            }
        }

        Map<String, Object> resultMap = new HashMap<>();
        PageResult<Map<String, Object>> result = new PageResult<>(total, conversations);
        resultMap.put("conversations", result);

        // 未读消息数量（不包含系统消息）
        Integer letterUnreadCount = messageService.findLetterUnreadCount(user.getId(), null);
        resultMap.put("letterUnreadCount", letterUnreadCount);
        return Result.ok().data(resultMap);
    }

    @RequiresPermissions({"message:detail"})
    @GetMapping("/pri/message/detail/{conversationId}")
    @ApiOperation(value = "私信详情", notes = "根据conversationId获取详细的私信")
    public Result getLetterDetail(
            @PathVariable("conversationId") String conversationId,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "8") Integer limit
    ) {
        List<Message> letterList = messageService.findLetters(conversationId, page, limit);
        Integer total = messageService.findLetterCount(conversationId);

        List<Map<String, Object>> letters = new ArrayList<>();
        if (letters != null) {
            for (Message message : letterList) {
                Map<String, Object> map = new HashMap<>();
                map.put("letter", message);
                map.put("fromUser", userService.findUserById(message.getFromId()));
                letters.add(map);
            }
        }

        Map<String, Object> result = new HashMap<>();
        // 私信详情列表
        result.put("letters", new PageResult<>(total, letters));
        // 私信目标
        result.put("target", getLetterTarget(conversationId));

        // 将未读的消息设置为已读
        List<Integer> ids = getLetterIds(letterList);
        if (!ids.isEmpty()) {
            messageService.readMessage(ids);
        }

        return Result.ok().data(result);
    }

    @RequiresPermissions({"message:send"})
    @PostMapping("/pri/message/send")
    @ApiOperation(value = "发送私信", notes = "根据用户id给用户发送私信")
    public Result sendLetter(@RequestBody @Valid Message message) {
        messageService.addMessage(message);
        return Result.ok(ResultCode.SEND_LETTER_SUCCESS);
    }

    private List<Integer> getLetterIds(List<Message> letterList) {
        List<Integer> ids = new ArrayList<>();

        if (letterList != null) {
            for (Message message : letterList) {
                if (CurrentUser.getCurrentUser().getId() == message.getToId() && message.getStatus() == false) {
                    ids.add(message.getId());
                }
            }
        }

        return ids;
    }

    private User getLetterTarget(String conversationId) {
        String[] ids = conversationId.split("_");
        Integer id0 = Integer.parseInt(ids[0]);
        Integer id1 = Integer.parseInt(ids[1]);

        if (CurrentUser.getCurrentUser().getId() == id0) {
            return userService.findUserById(id1);
        } else {
            return userService.findUserById(id0);
        }
    }
}
