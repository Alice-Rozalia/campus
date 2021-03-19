package org.kuro.campus.service;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/3/9 14:05
 */
public interface LikeService {

    // 点赞
    void like(Integer userId, Integer entityType, Integer entityId);

    // 查询某实体点赞的数量
    Long findEntityLikeCount(Integer entityType, Integer entityId);

    // 查询某人对某实体的点赞状态
    Integer findEntityLikeStatus(Integer userId, Integer entityType, Integer entityId);
}
