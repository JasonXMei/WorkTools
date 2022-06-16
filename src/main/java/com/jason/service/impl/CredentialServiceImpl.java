package com.jason.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.entity.Credential;
import com.jason.mapper.CredentialMapper;
import com.jason.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jason
 * @since 2022-06-14
 */
@Service
public class CredentialServiceImpl extends ServiceImpl<CredentialMapper, Credential> implements CredentialService {

    @Autowired
    private CredentialMapper credentialMapper;

    @Override
    public Credential getByParam(Credential credential) {
        return credentialMapper.getByParam(credential);
    }

}
