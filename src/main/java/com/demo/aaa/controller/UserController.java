package com.demo.aaa.controller;

import com.demo.aaa.common.vo.Result;
import com.demo.aaa.entity.User;
import com.demo.aaa.service.IUserService;
import com.demo.aaa.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author guan
 * @since 2023-05-02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/all")
    //@ResponseBody
    public Result<List<User>> getAll(){
        return Result.success(userService.list(), "查询成功");
    }
    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody User user){
        Map<String,Object> map=userService.login(user);
        if(map!=null){
            return Result.success(map);
        }
        return Result.fail("用户名或者密码错误");
    }
    @GetMapping("/info")
    public Result<?> getUserInfo(@RequestParam("token")String token){
        //根据token来到redis中获取用户信息
        Map<String,Object> map=userService.getUserInfo(token);
        if(map!=null){
            return Result.success(map);
        }
        return Result.fail(20002,"用户登录信息无效");
    }
    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token")String token){
         userService.logout(token);
         return Result.success();
    }

}
