package com.mrl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrl.entity.Emp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpDao extends BaseMapper<Emp> {
    Boolean deleteEmp(String id);
}
