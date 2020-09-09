package cn.cherry.blog.service.impl;

import cn.cherry.blog.domain.UsersInfo;
import cn.cherry.blog.mapper.UsersInfoMapper;
import cn.cherry.blog.service.UsersInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 用户信息操作
 * @author: Cherry
 * @create: 2020-09-08 14:20
 **/
@Service
@Slf4j
public class UsersInfoServiceImpl implements UsersInfoService {

    @Autowired
    private UsersInfoMapper usersInfoMapper;


    @Override
    public Boolean addUserInfo(UsersInfo usersInfo) {
        int row = usersInfoMapper.insert(usersInfo);
        return row == 1;
    }

    @Override
    public Boolean checkUserName(String userName) {
        Integer row = usersInfoMapper.getCountByUserName(userName);
        return row == 0;
    }
}
