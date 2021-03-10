package org.kuro.campus.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @Date: 2021/3/9 20:47
 */
@ApiModel(value="Swiper对象", description="轮播图片表")
@Table(name = "tb_swiper")
@Data
public class Swiper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "图片地址")
    private String url;

    @ApiModelProperty(value = "是否可用")
    private Boolean status;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date create_time;
}
