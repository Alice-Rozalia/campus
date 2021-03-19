package org.kuro.campus.service.impl;

import org.kuro.campus.mapper.PermissionMapper;
import org.kuro.campus.model.entity.Permission;
import org.kuro.campus.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/6 20:11
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取所有的权限
     * @return
     */
    @Override
    public List<Permission> getAllPermissions() {
        // 先查询 redis缓存 中是否存在 permission 集合
        List<Permission> permissions = redisTemplate.opsForList().range("permissions", 0, -1);
        if (permissions == null || permissions.size() == 0) {
            permissions = permissionMapper.findAllPermission();
            // 将数据存储到 redis，再返回
            redisTemplate.opsForList().rightPushAll("permissions", permissions);
        }
        return permissions;
    }
}
