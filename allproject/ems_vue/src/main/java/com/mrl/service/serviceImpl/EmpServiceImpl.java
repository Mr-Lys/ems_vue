package com.mrl.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrl.dao.EmpDao;
import com.mrl.entity.Emp;
import com.mrl.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpServiceImpl extends ServiceImpl<EmpDao, Emp> implements EmpService {

    @Autowired
    private EmpDao empDao;

    @Override
    public Boolean delete(String id) {

        return empDao.deleteEmp(id);
    }
}
