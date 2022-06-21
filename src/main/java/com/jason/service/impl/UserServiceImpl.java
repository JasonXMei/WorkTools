package com.jason.service.impl;

import com.jason.entity.User;
import com.jason.mapper.UserMapper;
import com.jason.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @Author Jason
 * @since 2022-06-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
