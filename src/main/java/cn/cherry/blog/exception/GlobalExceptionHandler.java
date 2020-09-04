package cn.cherry.blog.exception;

import cn.cherry.blog.common.ResponseEnum;
import cn.cherry.blog.common.ResponseResult;
import cn.cherry.blog.common.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @description: 全局异常处理
 * @author: Cherry
 * @create: 2020-09-04 18:02
 **/
@ResponseBody
@ControllerAdvice(basePackages = "cn.cherry.blog.controller")
public  class GlobalExceptionHandler {
    private static  final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";
    private static Logger log = LoggerFactory.getLogger(cn.cherry.blog.exception.GlobalExceptionHandler.class);

    /**
    * @Description: 运行时异常
    * @Param: 
    * @return: 
    */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult runtimeExceptionHandler(RuntimeException ex){
        return resultForamt(ResponseEnum.FAIL.getResCode(),ex);
    }

    /**
    * @Description: 空指针异常
    * @Param:
    * @return:
    */
    @ExceptionHandler(NullPointerException.class)
    public ResponseResult nullPointerExceptionHandler(NullPointerException ex){
        return resultForamt(ResponseEnum.FAIL.getResCode(),ex);
    }

    /**
    * @Description: 类型转换异常
    * @Param:
    * @return:
    */
    @ExceptionHandler(ClassCastException.class)
    public ResponseResult classCastExceptionHandler(ClassCastException ex){
        return resultForamt(ResponseEnum.FAIL.getResCode(),ex);
    }

    /**
     * @Description: IO异常
     * @Param:
     * @return:
     */
    @ExceptionHandler({IOException.class})
    public ResponseResult iOExceptionHandler(IOException ex){
        return resultForamt(ResponseEnum.FAIL.getResCode(),ex);
    }

    /**
     * @Description: 未知方法异常
     * @Param:
     * @return:
     */
    @ExceptionHandler(NoSuchMethodException.class)
    public ResponseResult noSuchMethodExceptionHandler(NoSuchMethodException ex){
        return resultForamt(ResponseEnum.FAIL.getResCode(),ex);
    }

    /**
     * @Description: 数组越界异常
     * @Param:
     * @return:
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseResult indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex){
        return resultForamt(ResponseEnum.FAIL.getResCode(),ex);
    }

    /**
     * @Description: 400错误
     * @Param:
     * @return:
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseResult requestNotReadable(HttpMessageNotReadableException ex){
        log.info("400...requestNotReadable");
        return resultForamt(ResponseEnum.FAIL.getResCode(),ex);
    }

    /**
     * @Description: 400错误
     * @Param:
     * @return:
     */
    @ExceptionHandler({TypeMismatchException.class})
    public ResponseResult requestTypeMismatch(TypeMismatchException ex){
        log.info("400...requestTypeMismatchException");
        return resultForamt(ResponseEnum.FAIL.getResCode(),ex);
    }

    /**
     * @Description: 400错误
     * @Param:
     * @return:
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseResult requestMissingServletRequest(MissingServletRequestParameterException ex){
        log.info("400...requestMissingServletRequest");
        return resultForamt(ResponseEnum.FAIL.getResCode(),ex);
    }

    /**
     * @Description: 405错误
     * @Param:
     * @return:
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseResult request405(HttpRequestMethodNotSupportedException ex){
        return resultForamt(ResponseEnum.FAIL.getResCode(),ex);
    }

    /**
     * @Description: 406错误
     * @Param:
     * @return:
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public ResponseResult request406(HttpMediaTypeNotAcceptableException ex){
        log.info("406...");
        return resultForamt(ResponseEnum.FAIL.getResCode(),ex);
    }

    /**
     * @Description: 400错误
     * @Param:
     * @return:
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseResult server500(RuntimeException ex){
        log.info("500...");
        return resultForamt(ResponseEnum.FAIL.getResCode(),ex);
    }

    /**
     * @Description: 栈溢出
     * @Param:
     * @return:
     */
    @ExceptionHandler({StackOverflowError.class})
    public ResponseResult requestStackOverflow(StackOverflowError ex){
        return resultForamt(ResponseEnum.FAIL.getResCode(),ex);
    }

    /**
     * @Description: 处理接口数据验证异常
     * @Param:
     * @return:
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("handleMethodArgumentNotValidException...",e);
        Map<String,String> map = e.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage));

        return ResponseUtil.fail(ResponseEnum.FAIL.getResCode(),map.toString());
    }

    /**
    * @Description: 其他错误
    * @Param: 
    * @return: 
    */
    @ExceptionHandler({Exception.class})
    public  ResponseResult exception(Exception ex){
        return resultForamt(ResponseEnum.FAIL.getResCode(),ex);
    }



    private <T extends  Throwable> ResponseResult resultForamt(String resCode,T ex){
        log.error(String.format(logExceptionFormat,resCode,ex.getMessage()));
        return ResponseUtil.fail(resCode,ex.getMessage());
    }

}
