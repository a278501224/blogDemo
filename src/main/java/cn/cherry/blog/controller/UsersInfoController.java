package cn.cherry.blog.controller;

import cn.cherry.blog.common.ResponseEnum;
import cn.cherry.blog.common.ResponseResult;
import cn.cherry.blog.domain.UsersInfo;
import cn.cherry.blog.service.UsersInfoService;
import cn.cherry.blog.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 增删改查用户信息
 * @author: Cherry
 * @create: 2020-09-08 14:11
 **/
@Slf4j
@Api(tags = "后台用户信息操作")
@RestController
@RequestMapping("/usersInfo")
public class UsersInfoController {
    @Autowired
    private UsersInfoService usersInfoService;

    @Value("${public.key}")
    private String publicKey;


    @ApiOperation("校验用户名是否存在")
    @PostMapping("/checkUserName")
    public ResponseResult checkUserName(@RequestParam(name = "userName") String userName){
        Boolean result = usersInfoService.checkUserName(userName);
        return result?new ResponseResult(ResponseEnum.SUCCESS.getResCode(),ResponseEnum.SUCCESS.getResMsg(),true):new ResponseResult(ResponseEnum.USER_NAME_EXIST.getResCode(),ResponseEnum.USER_NAME_EXIST.getResMsg(),false);
    }


    @ApiOperation("校验手机号是否存在")
    @PostMapping("/checkUserPhone")
    public ResponseResult checkUserPhone(@RequestParam(name = "userPhone") String userPhone){
        Boolean result = usersInfoService.checkUserPhone(userPhone);
        return result?new ResponseResult(ResponseEnum.SUCCESS.getResCode(),ResponseEnum.SUCCESS.getResMsg(),true):new ResponseResult(ResponseEnum.USER_PHONE_EXIST.getResCode(),ResponseEnum.USER_PHONE_EXIST.getResMsg(),false);
    }


    @ApiOperation("新增用户")
    @PostMapping("/addUserInfo")
    public ResponseResult addUserInfo(@RequestBody UsersInfo usersInfo){
        //TODO 校验入参不能为空

        usersInfo.setUserPassword(SecurityUtil.encrypt(usersInfo.getUserPassword(), publicKey));
        Boolean result = usersInfoService.addUserInfo(usersInfo);
        return result?new ResponseResult(ResponseEnum.SUCCESS.getResCode(),ResponseEnum.SUCCESS.getResMsg(),true):new ResponseResult(ResponseEnum.FAIL.getResCode(),ResponseEnum.FAIL.getResMsg(),false);
    }


    @ApiOperation("修改用户信息")
    @PostMapping("/editUserInfo")
    public ResponseResult editUserInfo(@RequestBody UsersInfo usersInfo){
        if(StringUtils.isBlank(usersInfo.getUpdatedBy()))
            return new ResponseResult(ResponseEnum.UPDATED_BY_NOT_NULL.getResCode(),ResponseEnum.UPDATED_BY_NOT_NULL.getResMsg(),false);
        if(StringUtils.isBlank(usersInfo.getCreatedBy()))
            return new ResponseResult(ResponseEnum.USER_NAME_NOT_NULL.getResCode(),ResponseEnum.USER_NAME_NOT_NULL.getResMsg(),false);

        String userPassword = usersInfo.getUserPassword();
        String userPhone = usersInfo.getUserPhone();
        if(StringUtils.isBlank(userPassword)){
            //加密
            usersInfo.setUserPassword(SecurityUtil.encrypt(userPassword,publicKey));
            if(StringUtils.isBlank(userPhone)){
                return new ResponseResult(ResponseEnum.USER_PHONE_NOT_NULL.getResCode(),ResponseEnum.USER_PHONE_NOT_NULL.getResMsg(),false);
            }
        }


        Boolean result = usersInfoService.editUserInfo(usersInfo);
        return result?new ResponseResult(ResponseEnum.SUCCESS.getResCode(),ResponseEnum.SUCCESS.getResMsg(),true):new ResponseResult(ResponseEnum.FAIL.getResCode(),ResponseEnum.FAIL.getResMsg(),false);
    }







}
