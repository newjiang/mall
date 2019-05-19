package com.shao.mall.common.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author newjiang
 * @date 2019/5/19 10:33
 * @description: 响应错误信息实体类
 */
@Setter
@Getter
public class ResponseError {

    // 错误码
    private int code;
    // 错误信
    private String message;
    // 失败标志
    private Boolean success = false;

    public ResponseError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseErrorEntity{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
