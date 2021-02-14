package org.kuro.campus.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kuro.campus.model.entity.Permission;
import org.kuro.campus.model.entity.Role;
import org.kuro.campus.model.entity.User;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveUser {

    /**
     * 当前用户对象
     */
    private User user;
    /**
     * 当前用户具有的角色
     */
    private List<Role> roles;
    /**
     * 当前用户具有的权限API:例如[user:add],[user:delete]...
     */
    private Set<String> permissions;

}
