package com.mrl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_emp")
public class Emp {
    @TableId(value = "id",type= IdType.ASSIGN_UUID)
    private String id;

    private String name;
    private String path;
    private Double salary;
    private Integer age;
}
