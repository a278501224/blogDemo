package cn.cherry.blog.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 统一响应实体类
 * @author: Cherry
 * @create: 2020-09-04 13:42
 **/

@Data
public class ResponseResult<T> implements Serializable {
    private String respCode;
    private String respMsg;
    private Boolean success;
    private T data;


    public ResponseResult(String respCode, String respMsg, Boolean success, T data) {
        this.respCode = respCode;
        this.respMsg = respMsg;
        this.success = success;
        this.data = data;
    }

    public ResponseResult(String respCode, String respMsg, Boolean success) {
        this.respCode = respCode;
        this.respMsg = respMsg;
        this.success = success;
    }

    public ResponseResult(String respCode, String respMsg, T data) {
        this.respCode = respCode;
        this.respMsg = respMsg;
        this.data = data;
    }


}
