package com.mrl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@TableName("t_user")
@Accessors(chain = true)
public class User {
    @TableId("id")
    private Integer id; // String 类型api
    private String name;
    private String realname;
    private String password;
    private String sex;
    private String status;
    private Date registerTime;
}
