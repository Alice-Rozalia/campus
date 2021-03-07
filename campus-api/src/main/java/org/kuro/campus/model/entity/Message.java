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
 * @Date: 2021/3/2 22:36
 */
@Data
@ApiModel(value = "Message对象", description = "消息表")
@Table(name = "tb_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "消息的发送者")
    private Integer fromId;

    @ApiModelProperty(value = "消息的接收者")
    private Integer toId;

    @ApiModelProperty(value = "查询字段")
    private String conversationId;

    @NotBlank(message = "消息内容不能为空！")
    @ApiModelProperty(value = "消息内容")
    private String content;

    // true表示已读，false表示未读
    @ApiModelProperty(value = "状态")
    private Boolean status;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;
}
