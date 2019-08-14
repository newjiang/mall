package com.jiang.mall.common.en;

/**
 * @author newjiang
 * @date 2019/6/3 22:39
 * @description: 基本错误的枚举
 * code在[100-499]之间
 */
public enum BaseErrorEnum {

    INSERT_ERROR(100, "新增失败"),
    DELETE_ERROR(200, "删除失败"),
    UPDATE_ERROR(300, "更新失败");

    // 错误码
    private int code;

    // 错误信息
    private String message;

    BaseErrorEnum(int code, String message) {
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
