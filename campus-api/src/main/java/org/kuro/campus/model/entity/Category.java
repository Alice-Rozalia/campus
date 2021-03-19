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
 * @Date: 2021/2/4 14:32
 */
@Data
@ApiModel(value="Category对象", description="分类表")
@Table(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类图片")
    private String image;

    @ApiModelProperty(value = "分类父ID")
    private Integer parentId;

    @ApiModelProperty(value = "是否可用")
    private Boolean enabled;
}
