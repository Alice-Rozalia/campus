package org.kuro.campus.handler;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.response.ResultCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2020/12/27 9:28
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理特定异常类型,可以定义多个,这里只以ArithmeticException为例
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error(ResultCode.ARITHMETIC_EXCEPTION);
    }

    /**
     * 参数异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result error(MethodArgumentNotValidException e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error().message(e.getFieldError().getDefaultMessage());
    }

    /**
     * 数据库相关异常
     * @param e
     * @return
     */
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public Result error(SQLException e) {
        if (e instanceof MySQLIntegrityConstraintViolationException) {
            return Result.error(ResultCode.HAS_ASSOCIATED_DATA);
        } else {
            return Result.error(ResultCode.DATABASE_EXCEPTION);
        }
    }

    /**
     * 处理业务异常,我们自己定义的异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result error(BusinessException e) {
        e.printStackTrace();
        log.error(e.getErrMsg());
        return Result.error().code(e.getCode())
                .message(e.getErrMsg());
    }

    /**
     * 全局异常处理,没有指定异常的类型,不管什么异常都是可以捕获的
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error();
    }
}
