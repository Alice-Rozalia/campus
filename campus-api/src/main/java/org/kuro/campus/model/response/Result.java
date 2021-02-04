package org.kuro.campus.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2020/12/23 15:33
 */
@Data
public class Result {

    @ApiModelProperty(value = "请求是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回的状态码")
    private Integer code;

    @ApiModelProperty(value = "返回的消息")
    private String message;

    @ApiModelProperty(value = "返回的数据")
    private Map<String, Object> data = new HashMap<>();

    /**
     * 构造方法私有化,里面的方法都是静态方法
     * 达到保护属性的作用
     */
    private Result() {}

    /**
     * 这里是使用链式编程
     */
    public static Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.COMMON_FAIL.getCode());
        result.setMessage(ResultCode.COMMON_FAIL.getMessage());
        return result;
    }

    public static Result error(ResultCode resultCode){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    public static Result ok(ResultCode resultCode){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    /**
     * 自定义返回成功与否
     * @param success
     * @return
     */
    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
