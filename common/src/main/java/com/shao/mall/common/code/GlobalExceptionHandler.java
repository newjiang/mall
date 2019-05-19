package com.shao.mall.common.code;

import com.shao.mall.common.exception.BaseException;
import com.shao.mall.common.model.GlobalException;
import com.shao.mall.common.model.ResponseError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author newjiang
 * @date 2019/5/19 17:10
 * @description: 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // 自定义异常
    @ExceptionHandler(BaseException.class)
    public ResponseError customExceptionHandler(HttpServletRequest request, Exception exception, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        BaseException e = (BaseException) exception;
        return new ResponseError(e.getCode(), e.getMessage());
    }

    // RuntimeException 异常
    @ExceptionHandler(RuntimeException.class)
    public ResponseError runtimeExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        RuntimeException exception = (RuntimeException) e;
        return new ResponseError(GlobalException.RUNTIME_EXCEPTION_CODE.getCode(), exception.getMessage());
    }

    // 通用的接口映射异常处理方
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {

        if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) exception;
            return new ResponseEntity<>(new ResponseError(status.value(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage()), status);
        }

        if (exception instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException e = (MethodArgumentTypeMismatchException) exception;
            System.out.println(
                    "参数转换失败:" +
                            "方法:" + e.getParameter().getMethod().getName() + "," +
                            "参数:" + e.getName() + "," +
                            "信息:" + e.getLocalizedMessage());;
            return new ResponseEntity<>(new ResponseError(status.value(), "参数转换异常"), status);
        }

        return new ResponseEntity<>(new ResponseError(status.value(), "参数转换异常"), status);
    }
}

