package com.jason.mapper;

import com.jason.entity.Credential;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2022-06-14
 */
public interface CredentialMapper extends BaseMapper<Credential> {

    Credential getByParam(Credential credential);

}
