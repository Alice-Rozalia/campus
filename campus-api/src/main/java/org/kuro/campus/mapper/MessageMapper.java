package org.kuro.campus.mapper;

import org.apache.ibatis.annotations.Param;
import org.kuro.campus.model.entity.Message;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/3/2 22:38
 */
public interface MessageMapper extends Mapper<Message> {

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

    // 修改消息状态（将未读改为已读）
    Integer updateStatus(@Param("ids") List<Integer> ids);
}
