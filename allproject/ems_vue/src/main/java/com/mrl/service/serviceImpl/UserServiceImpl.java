package com.mrl.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrl.dao.UserDao;
import com.mrl.entity.User;
import com.mrl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional // 把事务全部交给spring管理
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        // 生成用户状态
        user.setStatus("已激活");
        // 设置注册时间
        user.setRegisterTime(new Date());
        userDao.insert(user);
    }
}
