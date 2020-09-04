package cn.cherry.blog.common;

/**
 * @description: 统一返回工具类
 * @author: Cherry
 * @create: 2020-09-04 14:37
 **/
public class ResponseUtil {
    /**
    * @Description: 成功,且返回体有数据
    * @Param:
    * @return:
    */
    public static ResponseResult success(Object object){
        return new ResponseResult<>(ResponseEnum.SUCCESS.getResCode(),ResponseEnum.SUCCESS.getResMsg(),true,object);
    }

    /**
    * @Description: 成功个,返回体没有数据
    * @Param:
    * @return:
    */
    public static ResponseResult success(){
        return new ResponseResult<>(ResponseEnum.SUCCESS.getResCode(),ResponseEnum.SUCCESS.getResMsg(),true);
    }

    /**
    * @Description: 失败返回信息
    * @Param:
    * @return:
    */
    public static ResponseResult fail(String resCode,String resMsg){
        return new ResponseResult<>(resCode,resMsg,false);
    }

    /**
    * @Description: 失败返回信息，具体错误信息在返回体中
    * @Param:
    * @return:
    */
    public static ResponseResult fail(String resCode,String resMsg,Object data){
        return new ResponseResult<>(resCode,resMsg,data);
    }


}
