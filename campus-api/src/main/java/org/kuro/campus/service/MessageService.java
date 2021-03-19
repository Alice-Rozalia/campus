package org.kuro.campus.service;

import org.kuro.campus.model.entity.Message;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/3/2 22:39
 */
public interface MessageService {

    // 查询当前用户的会话列表，针对每个会话只返回一条最新的私信
    List<Message> findConversations(Integer userId, Integer page, Integer limit);

    // 查询当前用户的会话数量
    Integer findConversationCount(Integer userId);

    // 查询某个会话所包含的私信列表
    List<Message> findLetters(String conversationId, Integer page, Integer limit);

    // 查询某个会话所包含的私信数量
    Integer findLetterCount(String conversationId);

    // 查询未读私信的数量
    Integer findLetterUnreadCount(Integer userId, String conversationId);

    // 新增消息
    Integer addMessage(Message message);

    // 修改消息状态（将未读改为已读）
    Integer readMessage(List<Integer> ids);
}
