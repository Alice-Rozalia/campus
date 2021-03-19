package org.kuro.campus.service;

import java.util.List;
import java.util.Map;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/3/9 14:43
 */
public interface FollowService {

    // 关注
    void follow(Integer userId, Integer entityType, Integer entityId);

    // 取消关注
    void unFollow(Integer userId, Integer entityType, Integer entityId);

    // 查询关注的实体的数量
    Long findFolloweeCount(Integer userId, Integer entityType);

    // 查询当前用户是否已关注该实体
    Boolean hasFollowed(Integer userId, Integer entityType, Integer entityId);

    // 查询某个用户关注的商品
    List<Map<String, Object>> findFollowees(Integer userId, Integer page, Integer limit);
}
