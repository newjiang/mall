package own.jiang.mall.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author newjiang
 * @date 2019/5/15 22:53
 * @description: 统一返回格式的工具类
 */
@Setter
@Getter
@ToString
public class Result<T> {

    // 是否成功的标志
    private Boolean success;
    // 返回的提示信息
    private String message;
    // 返回的数据
    private T data;

    /**
     * 请求成功返回的格式
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 请求成功返回的格式
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T t) {
        Result<T> result = new Result<>();
        result.setMessage(MessageEnum.SUCCESS.getValue());
        result.setSuccess(true);
        result.setData(t);
        return result;
    }

    /**
     * 请求失败的返回格式
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> failure(String message) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

    /**
     * 系统错误的返回格式
     *
     * @param error
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String error) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setMessage(error);
        return result;
    }

}
