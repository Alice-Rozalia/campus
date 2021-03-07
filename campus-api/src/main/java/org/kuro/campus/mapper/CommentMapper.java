package org.kuro.campus.mapper;

import org.kuro.campus.model.entity.Comment;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/28 11:04
 */
public interface CommentMapper extends Mapper<Comment> {

    List<Comment> findCommentsByEntity(Integer entityType, Integer entityId, Integer page, Integer limit);

    Integer findCountByEntity(Integer entityType, Integer entityId);
}
