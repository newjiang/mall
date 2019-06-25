package com.shao.mall.common.model;

/**
 * @author newjiang
 * @date 2019/6/3 22:39
 * @description: TODO
 */
public enum BaseExceptionEnum {

    INSERT_ERROR(100, "新增失败"),
    DELETE_ERROR(200, "删除失败"),
    UPDATE_ERROR(300, "更新失败"),
    QUERY_ERROR(400, "更新失败"),;

    // 错误码
    private int code;
    // 错误信
    private String message;

    BaseExceptionEnum(int code, String message) {
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
