package org.kuro.campus.service.impl;

import org.kuro.campus.mapper.PermissionMapper;
import org.kuro.campus.model.entity.Permission;
import org.kuro.campus.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 获取所以的权限
     * @return
     */
    @Override
    public List<Permission> getAllPermissions() {
        return permissionMapper.findAllPermission();
    }
}
