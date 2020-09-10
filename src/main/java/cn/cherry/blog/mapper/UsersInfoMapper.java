package cn.cherry.blog.mapper;

import cn.cherry.blog.domain.UsersInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * @description: 用户信息操作
 * @author: Cherry
 * @create: 2020-09-08 14:22
 **/
public interface UsersInfoMapper extends BaseMapper<UsersInfo> {
        Integer getCountByUserName(String userName);

        Integer getCountByUserPhone(String userPhone);

        Integer editUserInfo(UsersInfo usersInfo);

}
