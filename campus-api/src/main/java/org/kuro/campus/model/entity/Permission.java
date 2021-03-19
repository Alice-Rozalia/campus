package org.kuro.campus.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/5 21:55
 */
@Data
@ApiModel(value="Permission对象", description="权限表")
@Table(name = "tb_permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "权限标识")
    private String perms;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "是否可用")
    private Boolean status;

    @ApiModelProperty(value = "访问权限需要的角色")
    private List<Role> roles;
}
