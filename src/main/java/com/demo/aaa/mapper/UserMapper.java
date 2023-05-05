package com.demo.aaa.mapper;

import com.demo.aaa.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guan
 * @since 2023-05-02
 */
public interface UserMapper extends BaseMapper<User> {
    public User getById(Integer id);
}
