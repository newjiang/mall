package com.shao.mall.order.code;

import lombok.Data;

/**
 * @author newjiang
 * @date 2019/5/15 22:53
 * @description: 统一返回格式的工具类
 */
@Data
public class Result<T> {

    // 成功
    private final static boolean SUCCESS = true;
    // 失败
    private final static boolean FAILURE = false;

    // 是否成功的标志
    private Boolean success;
    // 返回的提示信息
    private Enum message;
    // 返回的数据
    private T data;

    /**
     * 请求成功返回的格式
     * @param <T>
     * @return
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setMessage(MessageEnum.success);
        result.setSuccess(SUCCESS);
        return result;
    }

    /**
     * 请求成功返回的格式
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T t) {
        Result<T> result = new Result<>();
        result.setMessage(MessageEnum.success);
        result.setSuccess(SUCCESS);
        result.setData(t);
        return result;
    }

    /**
     * 请求失败的返回格式
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> failure(Enum message) {
        Result<T> result = new Result<>();
        result.setSuccess(FAILURE);
        result.setMessage(message);
        return result;
    }

}
