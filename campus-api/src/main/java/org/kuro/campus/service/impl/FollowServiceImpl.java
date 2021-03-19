package org.kuro.campus.service.impl;

import org.kuro.campus.model.entity.Goods;
import org.kuro.campus.service.FollowService;
import org.kuro.campus.service.GoodsService;
import org.kuro.campus.utils.CustomConstant;
import org.kuro.campus.utils.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/3/9 14:43
 */
@Service
public class FollowServiceImpl implements FollowService, CustomConstant {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private GoodsService goodsService;

    @Override
    public void follow(Integer userId, Integer entityType, Integer entityId) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                // 关注
                String followeeKey = RedisKeyUtil.getFolloweeKey(userId, entityType);
                // 粉丝
                String followerKey = RedisKeyUtil.getFollowerKey(entityType, entityId);

                // 启用事务
                operations.multi();

                operations.opsForZSet().add(followeeKey, entityId, System.currentTimeMillis());
                operations.opsForZSet().add(followerKey, userId, System.currentTimeMillis());

                return operations.exec();
            }
        });
    }

    @Override
    public void unFollow(Integer userId, Integer entityType, Integer entityId) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String followeeKey = RedisKeyUtil.getFolloweeKey(userId, entityType);
                String followerKey = RedisKeyUtil.getFollowerKey(entityType, entityId);

                operations.multi();

                operations.opsForZSet().remove(followeeKey, entityId);
                operations.opsForZSet().remove(followerKey, userId);

                return operations.exec();
            }
        });
    }

    @Override
    public Long findFolloweeCount(Integer userId, Integer entityType) {
        String followeeKey = RedisKeyUtil.getFolloweeKey(userId, entityType);

        return redisTemplate.opsForZSet().zCard(followeeKey);
    }

    @Override
    public Boolean hasFollowed(Integer userId, Integer entityType, Integer entityId) {
        String followeeKey = RedisKeyUtil.getFolloweeKey(userId, entityType);
        return redisTemplate.opsForZSet().score(followeeKey, entityId) != null;
    }

    @Override
    public List<Map<String, Object>> findFollowees(Integer userId, Integer page, Integer limit) {
        page = page - 1;
        String followeeKey = RedisKeyUtil.getFolloweeKey(userId, ENTITY_TYPE_GOODS);

        Set<Integer> targetIds = redisTemplate.opsForZSet().reverseRange(followeeKey, page, page + limit - 1);
        if (targetIds == null) {
            return null;
        }

        List<Map<String, Object>> list = new ArrayList<>();
        for (Integer targetId : targetIds) {
            Map<String, Object> map = new HashMap<>();
            Goods goods = goodsService.findGoodsById(targetId);
            map.put("goods", goods);
            Double score = redisTemplate.opsForZSet().score(followeeKey, targetId);
            map.put("followTime", new Date(score.longValue()));
            list.add(map);
        }
        return list;
    }
}
