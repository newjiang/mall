package com.shao.mall.common.exception;

/**
 * @author newjiang
 * @date 2019/5/19 10:32
 * @description: 基本异常类
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -6097168122762579434L;

    private int code;

    public BaseException() {
        super();
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
