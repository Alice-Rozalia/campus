package org.kuro.campus.mapper;

import org.kuro.campus.model.entity.Permission;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/5 21:58
 */
public interface PermissionMapper extends Mapper<Permission> {

    List<Permission> findAllPermission();
}
