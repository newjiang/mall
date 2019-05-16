package com.shao.mall.common;

/**
 * @author newjiang
 * @date 2019/5/15 23:45
 * @description: 前端返回的提示信息枚举
 */
public enum MessageEnum {

    success("success"),
    failure("failure");

    private String value;

    MessageEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
