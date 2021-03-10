package org.kuro.campus.service;

import org.kuro.campus.model.entity.Comment;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/28 11:05
 */
public interface CommentService {

    List<Comment> findCommentByEntity(Integer entityType, Integer entityId, Integer page, Integer limit);

    Integer findCountByEntity(Integer entityType, Integer entityId);

    Integer addComment(Comment comment);

    Comment findCommentById(Integer id);
}
