package com.aaa.qy108.status;

/**
 * @Author guohang
 * @Description 查询的状态码
 * @Date 2020/5/12 23:12
 */
public enum  SelectStatus {

    SELECT_DATA_SUCCESS("24001", "查询数据成功"),
    SELECT_DATA_FAILED("14001", "查询数据失败"),
    SELECT_DATA_NOT_EXIST("14002","数据不存在！"),
    SELECT_DATA_BY_ID_SUCCESS("24002","根据ID查询数据成功！"),
    SELECT_DATA_BY_ID_FAILED("14003","根据ID查询数据失败！"),


    TEST("11111", "测试一下");

    SelectStatus(String code, String msg) {
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



