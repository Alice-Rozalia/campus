package org.kuro.campus.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/4 18:00
 */
@Data
@ApiModel(value = "Goods对象", description = "商品表")
@Table(name = "tb_goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "商品名称")
    @NotBlank(message = "商品名称不能为空！")
    private String name;

    @ApiModelProperty(value = "价格")
    @Digits(integer = 99999, fraction = 2)
    private Double price;

    @ApiModelProperty(value = "简介")
    @NotBlank(message = "简介不能为空！")
    private String introduce;

    @ApiModelProperty(value = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createDate;

    @ApiModelProperty(value = "浏览")
    private Integer look;

    @ApiModelProperty(value = "原价")
    private Double original;

    @ApiModelProperty(value = "是否下架")
    private Boolean enabled;

    @ApiModelProperty(value = "分类id")
    private Integer categoryId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;
}
