package org.kuro.campus.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.kuro.campus.utils.AddressUtil;
import org.kuro.campus.utils.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* org.kuro.campus.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ipAddr = IPUtil.getIpAddr(request);
        String location = AddressUtil.getCityInfo(ipAddr);
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ipAddr, classMethod, location, args.toString());
        logger.warn("请求对象：{}", requestLog);
    }

    @After("log()")
    public void doAfter() {
        logger.info("----------------------------- 执行完毕 ------------------------------");
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterRuturn(Object result) {
        logger.info("Result：{}", result);
    }

    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private String location;
        private Object args;

        public RequestLog() {
        }

        public RequestLog(String url, String ip, String classMethod, String location, Object args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.location = location;
            this.args = args;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getClassMethod() {
            return classMethod;
        }

        public void setClassMethod(String classMethod) {
            this.classMethod = classMethod;
        }

        public Object getArgs() {
            return args;
        }

        public void setArgs(Object args) {
            this.args = args;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", location='" + location + '\'' +
                    ", args=" + args +
                    '}';
        }
    }
}
