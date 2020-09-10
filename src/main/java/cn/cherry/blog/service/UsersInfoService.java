package cn.cherry.blog.service;

import cn.cherry.blog.domain.UsersInfo;
import io.swagger.models.auth.In;

import java.util.Map;

/**
 * @description: 用户信息操作
 * @author: Cherry
 * @create: 2020-09-08 14:18
 **/
public interface UsersInfoService {

    Boolean addUserInfo(UsersInfo usersInfo);

    Boolean checkUserName(String userName);

    Boolean checkUserPhone(String userPhone);

    Boolean editUserInfo(UsersInfo usersInfo);


}
