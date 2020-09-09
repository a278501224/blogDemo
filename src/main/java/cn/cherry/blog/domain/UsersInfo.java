package cn.cherry.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @description:
 * @author: Cherry
 * @create: 2020-09-08 09:25
 **/

@Data
@TableName(value = "blog_users_info")
public class UsersInfo {
    @TableId(type = IdType.UUID)
    private String id;
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    @NotEmpty(message = "密码不能为空")
    private String userPassword;
    private String userPhone;
    private String  createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
}
