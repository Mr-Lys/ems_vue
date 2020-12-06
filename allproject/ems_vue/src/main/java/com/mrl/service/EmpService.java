package com.mrl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mrl.entity.Emp;

public interface EmpService extends IService<Emp> {
    Boolean delete(String id);
}
