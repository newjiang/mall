package com.shao.mall.common.model;

/**
 * @author newjiang
 * @date 2019/5/19 18:15
 * @description: TODO
 */
public enum GlobalException {
    RUNTIME_EXCEPTION_CODE(999),
    ;

    private int code;

    GlobalException(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
