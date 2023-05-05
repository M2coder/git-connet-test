package com.demo.aaa;

import com.demo.aaa.entity.User;
import com.demo.aaa.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AaaApplicationTests {
    @Resource
    private UserMapper userMapper;

    @Test
    void testMapper() {
        List<User> list=userMapper.selectList(null);
        for (User x:
             list) {
            System.out.println(x.toString());
        }
    }

}
