package com.shao.mall.common.bean;

import com.shao.mall.common.exception.BaseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author newjiang
 * @date 2019/5/19 17:10
 * @description: 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // * 自定义异常
    @ExceptionHandler(BaseException.class)
    public ResponseError customExceptionHandler(HttpServletRequest request, Exception exception, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        BaseException e = (BaseException) exception;
        logger.error("基本异常:[" + e.getCode() + ":" + e.getMessage() + "]");
        return new ResponseError(e.getCode(), e.getMessage());
    }

    // RuntimeException 异常
    @ExceptionHandler(RuntimeException.class)
    public ResponseError runtimeExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {

        response.setStatus(HttpStatus.BAD_REQUEST.value());

        if (exception instanceof BadSqlGrammarException) {
            BadSqlGrammarException e = (BadSqlGrammarException) exception;
            logger.error("SQL语法错误:");
            logger.error(e.getSQLException());
            return new ResponseError(GlobalException.SQL_EXCEPTION);
        }

        RuntimeException e = (RuntimeException) exception;
        logger.error("运行时异常:");
        logger.error(e.getMessage());
        return new ResponseError(GlobalException.RUNNTIME_EXCEPTION);
    }

    // 通用的接口映射异常处理方
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        // * 校验@RequestParam
        if (exception instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException e = (MissingServletRequestParameterException) exception;
            String message = "参数[" + e.getParameterType() + ":" + e.getParameterName() + "]" + "必填";
            logger.error(message);
            return new ResponseEntity<>(new ResponseError(status.value(), message), status);
        }

        // * 校验JSON请求
        if (exception instanceof HttpMessageNotReadableException) {
            HttpMessageNotReadableException e = (HttpMessageNotReadableException) exception;
            logger.error("HTTP请求信息无法读取:");
            logger.error(e.getMessage());
            return new ResponseEntity<>(new ResponseError(status.value(), "HTTP请求信息无法读取"), status);
        }

        // * 校验参数
        if (exception instanceof BindException) {
            BindException e = (BindException) exception;
            BindingResult result = e.getBindingResult();
            List<String> list = new ArrayList<>();
            if (result.hasErrors()) {
                result.getAllErrors().forEach((error) -> {
                    FieldError fieldError = (FieldError) error;
                    // 属性
                    String field = fieldError.getField();
                    // 错误信息
                    String message = fieldError.getDefaultMessage();
                    logger.error("参数异常:[" + field + ":" + message + "]");
                    list.add(message);
                });
            }
            return new ResponseEntity<>(new ResponseError(status.value(), list.toString()), status);
        }
        // * 校验HttpMediaType
        if (exception instanceof HttpMediaTypeNotSupportedException) {
            HttpMediaTypeNotSupportedException e = (HttpMediaTypeNotSupportedException) exception;
            logger.error("HTTP请求媒体类型不支持:");
            logger.error(e.getMessage());
            return new ResponseEntity<>(new ResponseError(status.value(), "HTTP请求媒体类型不支持"), status);
        }
        if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) exception;
            logger.error("方法参数无效:");
            logger.error(e.getMessage());
            return new ResponseEntity<>(new ResponseError(status.value(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage()), status);
        }

        if (exception instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException e = (MethodArgumentTypeMismatchException) exception;
            logger.error(
                    "参数转换失败:" +
                            "方法:" + e.getParameter().getMethod().getName() + "," +
                            "参数:" + e.getName() + "," +
                            "信息:" + e.getLocalizedMessage());;
            return new ResponseEntity<>(new ResponseError(status.value(), "参数转换异常"), status);
        }

        logger.error("接口映射异常:");
        logger.error(exception.getMessage());
        return new ResponseEntity<>(new ResponseError(status.value(), "接口映射异常:" + exception.getMessage()), status);
    }

}

