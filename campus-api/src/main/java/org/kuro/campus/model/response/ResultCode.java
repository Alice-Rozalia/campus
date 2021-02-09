package org.kuro.campus.model.response;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2020/12/23 15:35
 */
public enum ResultCode implements CustomizeResultCode {
    /* 成功 */
    SUCCESS(200, "请求成功！"),
    LOGIN_SUCCESS(201, "登录成功！"),

    /* 默认失败 */
    COMMON_FAIL(999, "请求失败！"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "参数无效！"),
    PARAM_IS_BLANK(1002, "参数为空！"),
    PARAM_TYPE_ERROR(1003, "参数类型错误！"),
    PARAM_NOT_COMPLETE(1004, "参数缺失！"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "尚未登录，请登录！"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期！"),
    USER_CREDENTIALS_ERROR(2003, "账号或密码错误！"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期！"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用！"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定！"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在！"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在！"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号在别处登录！"),

    /* 业务错误 */
    NO_PERMISSION(3001, "权限不足！"),

    ADD_SUCCESS(3801, "添加成功！"),
    ADD_ERROR(3802, "添加失败！"),

    UPDATE_SUCCESS(3803, "修改成功！"),
    UPDATE_ERROR(3804, "修改失败！"),

    DELETE_SUCCESS(3805, "删除成功！"),
    DELETE_ERROR(3806, "删除失败！"),

    REGISTER_SUCCESS(4001, "注册成功！"),
    GOODS_PUBLISH_SUCCESS(4002,"商品发布成功！"),
    PHONE_IS_NOT_BIND(4003, "您尚未绑定手机号或填写地址，请先前往完善个人信息！"),

    /*运行时异常*/
    ARITHMETIC_EXCEPTION(9001,"算数异常！"),
    HAS_ASSOCIATED_DATA(9100, "该数据有关联数据，操作失败！"),
    DATABASE_EXCEPTION(9120, "数据库异常！");

    private Integer code;

    private String message;

    ResultCode(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
