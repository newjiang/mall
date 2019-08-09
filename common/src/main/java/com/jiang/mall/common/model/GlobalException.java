package com.jiang.mall.common.model;

/**
 * @author newjiang
 * @date 2019/5/19 18:15
 * @description: 全局异常枚举
 */
public enum GlobalException {

    SQL_EXCEPTION(500, "SQL异常"),

    RUNNTIME_EXCEPTION(999, "运行时异常"),
    ;

    private int code;
    private String message;

    GlobalException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
