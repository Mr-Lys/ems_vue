package com.mrl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrl.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
//    void save(@Param("user") User user);
}