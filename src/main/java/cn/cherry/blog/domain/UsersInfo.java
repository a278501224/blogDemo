package cn.cherry.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
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
    @Null
    private String id;
    private String userName;
    private String userPassword;
    private String userPhone;
    @NotEmpty
    private String  createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
}
