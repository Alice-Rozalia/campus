package org.kuro.campus.service;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/3/9 14:43
 */
public interface FollowService {

    // 关注
    void follow(Integer userId, Integer entityType, Integer entityId);

    // 取消关注
    void unFollow(Integer userId, Integer entityType, Integer entityId);
}
