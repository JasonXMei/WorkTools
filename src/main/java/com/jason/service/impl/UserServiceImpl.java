package com.jason.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.entity.Credential;
import com.jason.entity.User;
import com.jason.enums.ResponseCodeEnum;
import com.jason.exception.BusinessException;
import com.jason.mapper.UserMapper;
import com.jason.service.CredentialService;
import com.jason.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Collection;
import java.util.Objects;

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

    @Autowired
    private CredentialService credentialService;

    @Override
    @Transactional
    public void addAge(int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        queryWrapper.last("for update");

        User user = baseMapper.selectOne(queryWrapper);
        user.setAge(user.getAge() + 1);

        baseMapper.updateById(user);
    }

    @Override
    @Transactional
    public void testTransaction() {
        User user = baseMapper.selectById(1);
        user.setUpdatedAt(Calendar.getInstance().getTime());
        baseMapper.updateById(user);

        Credential credential = credentialService.getById(1);
        credential.setUpdatedAt(Calendar.getInstance().getTime());
        credentialService.saveOrUpdate(credential);

        throw new BusinessException(ResponseCodeEnum.INTERNAL_SERVER_ERROR, "system error");
    }

    @Override
    public boolean existsEmail(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);

        User user = baseMapper.selectOne(queryWrapper);
        if (Objects.nonNull(user)) {
            return true;
        }
        return false;
    }

    @Override
    public Collection<User> findByEmail(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);

        return baseMapper.selectList(queryWrapper);
    }
}
