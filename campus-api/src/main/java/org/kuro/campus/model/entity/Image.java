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
 * @Date: 2021/2/15 18:12
 */
@Data
@ApiModel(value = "Image对象", description = "图片表")
@Table(name = "tb_image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "图片地址")
    private String url;

    @ApiModelProperty(value = "实体id")
    private Integer entityId;
}
