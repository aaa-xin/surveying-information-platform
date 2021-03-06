package com.aaa.qy108.status;

/**
 * @Author guohang
 * @Description 登录的状态码枚举类
 * @Date 2020/5/12 22:15
 */
public enum  LoginStatus {

    LOGIN_SUCCESS("20001", "登录成功"),
    LOGIN_FAILED("10001", "登录失败"),
    USER_EXIST("20002", "用户存在"),
    USER_NOT_EXIST("10002", "用户不存在"),
    PASSWORD_WRONG("10003", "密码错误"),
    USER_LOCKED("10004", "账号被锁定"),
    LOGOUT_WRONG("10005", "用户退出异常"),
    LOGIN_TIMEOUT_EXIT("10006","超时自动退出，请重新登录"),
    NOT_LOGIN("10007", "用户没有登录，请登录后再试！"),

    TEST("11111", "测试一下");


    LoginStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}



