package org.kuro.campus.service;

import org.kuro.campus.model.entity.Permission;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/6 20:11
 */
public interface PermissionService {

    /**
     * 获取所以的权限
     * @return
     */
    List<Permission> getAllPermissions();
}
