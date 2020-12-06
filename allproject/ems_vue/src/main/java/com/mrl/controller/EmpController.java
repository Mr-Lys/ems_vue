package com.mrl.controller;

import com.mrl.entity.Emp;
import com.mrl.service.EmpService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("emp")
@ApiOperation("员工信息")
public class EmpController {
    @Autowired
    private EmpService empService;

    // 设置保存目录，upload.dir为application.propertity中的属性
    @Value("${upload.dir}")
    private String realPath;

    @ApiOperation("查询所有")
    @GetMapping("findAll")
    public List<Emp> findAll(){
        List<Emp> list = empService.list();
        log.info("查询到的用户信息是：[{}]",list);
        return list;
    }

    // 保存员工信息
    @ApiOperation("保存员工信息")
    @PostMapping("save")
    public Map<String,Object> save(Emp emp, MultipartFile photo){
        log.info("员工信息[{}]",emp.toString());
        log.info("头像信息[{}]",photo.getOriginalFilename());
        Map<String,Object> map = new HashMap<>();
        try {
            // 头像保存、需要重新生成文件名（怕重名）
            // .后边为扩展名的获取
            String newFileName = UUID.randomUUID().toString()+"."+ FilenameUtils.getExtension(photo.getOriginalFilename());
            //
            photo.transferTo(new File(realPath,newFileName));
            // 设置头像的保存地址
            emp.setPath(newFileName);
            empService.save(emp);
            map.put("status",true);
            map.put("msg","员工信息保存成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",false);
            map.put("msg","员工信息保存失败");
        }
        return map;
    }

    @ApiOperation("员工信息删除")
    @DeleteMapping("delete")
    public Map<String,Object> delete(String id){
        Map<String,Object> map = new HashMap<>();
        log.info("删除员工id"+id);
        try{
            // 删除员工头像
            Emp byId = empService.getById(id);
            File file = new File(realPath,byId.getPath());
            if (file.exists())
            {
                file.delete();
            }
            empService.delete(id);
            map.put("state",true);
            map.put("msg","删除成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","删除失败");
        }
        return map;
    }
}
