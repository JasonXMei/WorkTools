package com.jason.mapper;

import com.jason.entity.Credential;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @Author Jason
 * @since 2022-06-14
 */
public interface CredentialMapper extends BaseMapper<Credential> {

    /**
     * get data by param
     *
     * @param credential query object
     * @return Credential result
     */
    Credential getByParam(Credential credential);

}
