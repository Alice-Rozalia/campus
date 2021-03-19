package org.kuro.campus.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/28 10:50
 */
@Data
@ApiModel(value = "Comment对象", description = "评论表")
@Table(name = "tb_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "评论类型")
    private Integer entityType;

    @ApiModelProperty(value = "评论对象id")
    private Integer entityId;

    @ApiModelProperty(value = "回复对象id")
    private Integer targetId;

    @ApiModelProperty(value = "评论内容")
    @NotBlank(message = "评论内容不能为空！")
    private String content;

    @ApiModelProperty(value = "评论状态")
    private Boolean status;

    @ApiModelProperty(value = "评论时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;
}
