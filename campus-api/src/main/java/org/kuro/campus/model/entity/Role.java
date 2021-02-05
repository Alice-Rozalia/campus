package org.kuro.campus.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/5 21:49
 */
@Data
@ApiModel(value="Role对象", description="角色表")
@Table(name = "tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "角色标识")
    private String name;

    @ApiModelProperty(value = "角色中文名")
    private String describe;
}
