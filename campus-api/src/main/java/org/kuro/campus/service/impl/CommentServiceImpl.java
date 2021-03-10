package org.kuro.campus.service.impl;

import org.kuro.campus.mapper.CommentMapper;
import org.kuro.campus.model.entity.Comment;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.service.CommentService;
import org.kuro.campus.utils.CurrentUser;
import org.kuro.campus.utils.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/28 11:05
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Override
    public List<Comment> findCommentByEntity(Integer entityType, Integer entityId, Integer page, Integer limit) {
        page = (page - 1) * limit;
        return commentMapper.findCommentsByEntity(entityType, entityId, page, limit);
    }

    @Override
    public Integer findCountByEntity(Integer entityType, Integer entityId) {
        return commentMapper.findCountByEntity(entityType, entityId);
    }

    @Override
    public Integer addComment(Comment comment) {
        User user = CurrentUser.getCurrentUser();
        comment.setUserId(user.getId());
        comment.setCreateTime(new Date());
        // 过滤敏感词
        comment.setContent(sensitiveFilter.filter(comment.getContent()));
        return commentMapper.insertSelective(comment);
    }

    @Override
    public Comment findCommentById(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }
}
