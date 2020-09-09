package cn.cherry.blog.controller;

import cn.cherry.blog.common.ResponseEnum;
import cn.cherry.blog.common.ResponseResult;
import cn.cherry.blog.domain.UsersInfo;
import cn.cherry.blog.service.UsersInfoService;
import cn.cherry.blog.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 增删改查用户信息
 * @author: Cherry
 * @create: 2020-09-08 14:11
 **/
@Slf4j
@Api(tags = "用户信息操作")
@RestController
@RequestMapping("/usersInfo")
public class UsersInfoController {
    @Autowired
    private UsersInfoService usersInfoService;


    @ApiOperation("校验用户名是否存在")
    @PostMapping("/checkUserName")
    public ResponseResult checkUserName(@RequestParam(name = "userName") String userName){
        Boolean result = usersInfoService.checkUserName(userName);
        return result?new ResponseResult(ResponseEnum.SUCCESS.getResCode(),ResponseEnum.SUCCESS.getResMsg(),true):new ResponseResult(ResponseEnum.USER_NAME_EXIST.getResCode(),ResponseEnum.USER_NAME_EXIST.getResMsg(),false);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResponseResult addUserInfo(UsersInfo usersInfo){
        usersInfo.setUserPassword(SecurityUtil.encrypt(usersInfo.getUserPassword(), "0d7b43aca1c3f107"));
        Boolean result = usersInfoService.addUserInfo(usersInfo);
        return result?new ResponseResult(ResponseEnum.SUCCESS.getResCode(),ResponseEnum.SUCCESS.getResMsg(),true):new ResponseResult(ResponseEnum.FAIL.getResCode(),ResponseEnum.FAIL.getResMsg(),false);
    }


}
