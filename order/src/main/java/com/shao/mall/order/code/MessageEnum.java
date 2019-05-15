package com.shao.mall.order.code;

/**
 * @author newjiang
 * @date 2019/5/15 23:45
 * @description: TODO
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
