package com.mrl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mrl.entity.User;

public interface UserService extends IService<User>  {
    /**
     * 用户注册
     * @param user
     */
    void register(User user);
    User login(User user);
}
