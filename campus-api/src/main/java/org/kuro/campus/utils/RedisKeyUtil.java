package org.kuro.campus.utils;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/3/9 13:59
 */
public class RedisKeyUtil {

    private static final String SPLIT = ":";

    private static final String PREFIX_ENTITY_LIKE = "like:entity";

    private static final String PREFIX_FOLLOWEE = "followee";

    private static final String PREFIX_FOLLOWER = "follower";

    /**
     * 某个实体的赞
     * 以此种方式拼接  --> like:entity:entityType:entityId
     *
     * @param entityType
     * @param entityId
     * @return
     */
    public static String getEntityLikeKey(Integer entityType, Integer entityId) {
        return PREFIX_ENTITY_LIKE + SPLIT + entityType + SPLIT + entityId;
    }

    /**
     * 某个用户关注的实体
     * followee:userId:entityType --> zset(entityId, now)
     *
     * @param userId
     * @param entityType
     * @return
     */
    public static String getFolloweeKey(Integer userId, Integer entityType) {
        return PREFIX_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    /**
     * 某个实体拥有的粉丝
     * follower:entityType:entityId --> zset(userId, now)
     *
     * @param entityType
     * @param entityId
     * @return
     */
    public static String getFollowerKey(Integer entityType, Integer entityId) {
        return PREFIX_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }
}
