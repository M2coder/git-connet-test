package com.demo.aaa.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.aaa.entity.User;
import com.demo.aaa.mapper.UserMapper;
import com.demo.aaa.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guan
 * @since 2023-05-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public Map<String, Object> login(User user) {
        //根据用户名查询密码
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName,user.getName());
        queryWrapper.eq(User::getAge,user.getAge());
        User user1 = this.baseMapper.selectOne(queryWrapper);
        //结果不为空，则生成token，存入redis
        if(user1!=null){
            //生成token
            String  key = "key:"+UUID.randomUUID().toString();
            Map<String ,Object> map=new HashMap<>();
            //存入redis
            redisTemplate.opsForValue().set(key,user1,30, TimeUnit.MINUTES);

            map.put("token",key);
            return map;
        }

        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        Object obj = redisTemplate.opsForValue().get(token);
        if(obj!=null){
            User user=JSON.parseObject(JSON.toJSONString(obj),User.class);
            Map<String,Object> map= new HashMap<>();
            map.put("name",user.getName());
            map.put("score",user.getScore());
            return map;
        }

        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }
}
