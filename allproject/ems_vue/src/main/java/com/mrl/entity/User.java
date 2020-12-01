package com.mrl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@TableName("t_user")
@Accessors(chain = true)
public class User {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id; // String 类型api
    private String username;
    private String realname;
    private String password;
    private String sex;
    private String status;
    @TableField("registerTime")
    private Date registerTime;
}
