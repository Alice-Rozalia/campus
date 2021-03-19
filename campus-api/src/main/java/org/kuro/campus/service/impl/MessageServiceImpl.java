package org.kuro.campus.service.impl;

import org.kuro.campus.mapper.MessageMapper;
import org.kuro.campus.model.entity.Message;
import org.kuro.campus.service.MessageService;
import org.kuro.campus.utils.CurrentUser;
import org.kuro.campus.utils.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/3/2 22:39
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private SensitiveFilter filter;

    @Override
    public List<Message> findConversations(Integer userId, Integer page, Integer limit) {
        page = (page - 1) * limit;
        return messageMapper.findConversations(userId, page, limit);
    }

    @Override
    public Integer findConversationCount(Integer userId) {
        return messageMapper.findConversationCount(userId);
    }

    @Override
    public List<Message> findLetters(String conversationId, Integer page, Integer limit) {
        page = (page - 1) * limit;
        return messageMapper.findLetters(conversationId, page, limit);
    }

    @Override
    public Integer findLetterCount(String conversationId) {
        return messageMapper.findLetterCount(conversationId);
    }

    @Override
    public Integer findLetterUnreadCount(Integer userId, String conversationId) {
        return messageMapper.findLetterUnreadCount(userId, conversationId);
    }

    @Override
    public Integer addMessage(Message message) {
        message.setContent(filter.filter(message.getContent()));
        message.setCreateTime(new Date());
        message.setFromId(CurrentUser.getCurrentUser().getId());
        if (message.getFromId() < message.getToId()) {
            message.setConversationId(message.getFromId() + "_" + message.getToId());
        } else {
            message.setConversationId(message.getToId() + "_" + message.getFromId());
        }
        return messageMapper.insertSelective(message);
    }

    @Override
    public Integer readMessage(List<Integer> ids) {
        return messageMapper.updateStatus(ids);
    }
}
