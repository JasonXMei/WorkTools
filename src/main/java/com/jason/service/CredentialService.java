package com.jason.service;

import com.jason.entity.Credential;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jason
 * @since 2022-06-14
 */
public interface CredentialService extends IService<Credential> {

    Credential getByParam(Credential credential);
}
