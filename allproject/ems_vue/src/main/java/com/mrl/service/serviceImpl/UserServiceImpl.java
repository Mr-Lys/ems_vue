package com.mrl.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrl.dao.UserDao;
import com.mrl.entity.User;
import com.mrl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional // 把事务全部交给spring管理
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        // 根据用户输入的用户名判断用户是否存在
        List<User> username = userDao.selectList(new QueryWrapper<User>().eq("username", user.getUsername()));
        if (username.size() > 0){
            throw new RuntimeException("用户名已存在！");
        }
        // 生成用户状态
        user.setStatus("已激活");
        // 设置注册时间
        user.setRegisterTime(new Date());
        log.info("要存入数据库的数据是:[{}]",user);
        userDao.insert(user);
    }
}
