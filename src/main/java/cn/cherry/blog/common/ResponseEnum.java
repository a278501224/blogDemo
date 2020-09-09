package cn.cherry.blog.common;

/**
 * @description: 统一响应枚举类
 * @author: Cherry
 * @create: 2020-09-04 14:43
 **/
public enum ResponseEnum {
    SUCCESS("000000","成功"),
    FAIL("000001","失败"),
    UNAUTHORIZED("401","访问此资源需要完全身份验证"),
    USER_NAME_EXIST("000002","用户名已存在");




    private String resCode;
    private String resMsg;

    public String getResCode(){
        return resCode;
    }

    public String getResMsg(){
        return resMsg;
    }

    ResponseEnum(String resCode, String resMsg) {
        this.resCode = resCode;
        this.resMsg = resMsg;
    }
}
