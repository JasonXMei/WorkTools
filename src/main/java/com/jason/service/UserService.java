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

    /**
     * add age
     * @param userId
     */
    void addAge(int userId);

    /**
     * test mysql transaction
     */
    void testTransaction();

    /**
     * exist
     * @param email
     * @return
     */
    boolean existsEmail(String email);

    /**
     * find by email
     * @param email
     * @return
     */
    Collection<User> findByEmail(String email);
}
