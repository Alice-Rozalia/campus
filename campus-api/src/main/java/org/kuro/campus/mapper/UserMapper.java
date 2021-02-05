package org.kuro.campus.mapper;

import org.apache.ibatis.annotations.Param;
import org.kuro.campus.model.entity.Role;
import org.kuro.campus.model.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/5 21:57
 */
public interface UserMapper extends Mapper<User> {

    User loadUserByUsername(@Param("username") String username);

    List<Role> getRolesById(@Param("id") Integer id);
}
