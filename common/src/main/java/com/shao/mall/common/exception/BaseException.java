package com.shao.mall.common.exception;

import com.shao.mall.common.model.BaseExceptionEnum;

/**
 * @author newjiang
 * @date 2019/5/19 10:32
 * @description: 基本异常类
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -6097168122762579434L;

    private int code;

    public BaseException(String s) {
        super(s);
    }

    public BaseException(BaseExceptionEnum e) {
        super(e.getMessage());
        this.setCode(e.getCode());
    }

    public BaseException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}

