package cn.cherry.blog.validate;

import cn.cherry.blog.common.ResponseEnum;
import cn.cherry.blog.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.util.Map;
import java.util.Objects;

/**
 * @description: 校验参数是否为空
 * @author: Cherry
 * @create: 2020-09-10 14:36
 **/
@Slf4j
public class ValidateParameter {

    /**
    * @Description: 校验入参是否为空
    * @Param: 入参参数
    * @return: 
    */
    public static void checkInParam(Map<String,Object> inParam) throws Exception {
            for (Map.Entry<String,Object> entry : inParam.entrySet()){
                if(Objects.isNull(entry.getValue()) || Objects.equals("",entry.getValue())){
                    log.info("{}不能为空",entry.getKey());
                    throw new Exception(entry.getKey()+"不能为空");
                }
            }
    }

}
