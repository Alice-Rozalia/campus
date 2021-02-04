package org.kuro.campus.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/4 18:00
 */
@Data
@ApiModel(value="Goods对象", description="商品表")
@Table(name = "tb_goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "价格")
    private Double price;

    @ApiModelProperty(value = "简介")
    private String introduce;

    @ApiModelProperty(value = "发布时间")
    private Date createDate;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "图片")
    private String images;

    @ApiModelProperty(value = "浏览")
    private Integer look;

    @ApiModelProperty(value = "原价")
    private Double original;

    @ApiModelProperty(value = "是否下架")
    private Boolean enabled;
}
