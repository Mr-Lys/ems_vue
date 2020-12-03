package com.mrl.controller;

import com.mrl.entity.User;
import com.mrl.service.UserService;
import com.mrl.utils.VerifyCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户测试接口")
@Slf4j
@RestController
// 允许跨域
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 生成验证码图片
     */
    @GetMapping("getImage")
    public String getImageCode(HttpServletRequest request) throws IOException {
        // 1.使用工具类生成验证码(4位长度)
        String code  = VerifyCodeUtils.generateVerifyCode(4);
        // 2.使用base64将其存入servletContext作用域，前后端分离无session概念
        request.getServletContext().setAttribute("code",code);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();// 内存当中的输出流
//        设置图片宽高为220，60,
        VerifyCodeUtils.outputImage(120,40,byteArrayOutputStream,code);
        // 3、将图片转为base64,且进行拼接
        String s = "data:image/png;base64," +Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
        log.info("请求后台"+s);
        return s;
    }

    // 用来处理用户注册
    @ApiOperation("用户注册接口")
    @PostMapping("register")
    public Map<String,Object> register(@RequestBody User user,String code,HttpServletRequest request){
        log.info("用户信息:[{}]",user.toString());
        log.info("用户输入的验证码信息:[{}]",code);
        Map<String,Object> map = new HashMap<>();
        // 调用业务方法完成用户注册
        try {
            // 获取之前存储的验证码
            String key = (String) request.getServletContext().getAttribute("code");
            log.info("系统产生的验证码为:[{}]",key);
            /**
             * equalsIgnoreCase为忽略大小写的字符串比较方式
             */
            if (key.equalsIgnoreCase(code)){
                userService.register(user);
                map.put("status",true);
                map.put("msg","注册成功！");
            }else {
                throw new RuntimeException("验证码错误！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
            // 将其他异常捕获的信息进行打印
            map.put("msg","提示："+e.getMessage());
        }
        return map;
    }

    @ApiOperation("根据输入的用户名进行查询")
    @PostMapping("login")
    public Map<String,Object> login(@ApiParam("user")@RequestBody User user){
        log.info("当前登录用户的信息[{}]",user);
        Map<String,Object> map = new HashMap<>();
        try{
            User userDB = userService.login(user);
            map.put("status",true);
            map.put("msg","登录成功");
            map.put("result",userDB);
        }catch(Exception e){
            e.printStackTrace();
            map.put("status",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }
}
