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
 * @Date: 2021/3/17 10:57
 */
@Data
@ApiModel(value = "LoginLog对象", description = "登录日志表")
@Table(name = "tb_login_log")
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date loginTime;

    @ApiModelProperty(value = "登录地址")
    private String location;

    @ApiModelProperty(value = "ip地址")
    private String ip;
}
