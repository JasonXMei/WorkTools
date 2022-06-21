package com.jason.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jason.entity.Credential;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @Author Jason
 * @since 2022-06-14
 */
public interface CredentialService extends IService<Credential> {

    /**
     * get data by param
     *
     * @param credential query object
     * @return Credential result
     */
    Credential getByParam(Credential credential);

}
