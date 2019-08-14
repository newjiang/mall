package com.jiang.mall.common.en;

/**
 * @author newjiang
 * @date 2019/5/19 18:15
 * @description: 全局异常枚举
 * code在[500-999]之间
 */
public enum GlobalException {

    SQL_EXCEPTION(500, "SQL异常"),
    RUNNTIME_EXCEPTION(999, "运行时异常"),;

    // 错误码
    private int code;

    // 错误信息
    private String message;

    GlobalException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
