package com.jason.service;

import com.jason.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @Author Jason
 * @since 2022-06-09
 */
public interface UserService extends IService<User> {

    void addAge(int userId);

    void testTransaction();

    boolean existsEmail(String email);

    Collection<User> findByEmail(String email);
}
