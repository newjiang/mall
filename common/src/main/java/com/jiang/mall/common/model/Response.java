package com.jiang.mall.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author newjiang
 * @date 2019/5/19 10:33
 * @description: 响应错误信息实体类
 */
@Setter
@Getter
@ToString
public class Response {

    // 错误码
    private int code;

    // 错误信
    private String message;

    // 失败标志
    private Boolean success = false;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(GlobalException e) {
        this.code = e.getCode();
        this.message = e.getMessage();
    }

}
