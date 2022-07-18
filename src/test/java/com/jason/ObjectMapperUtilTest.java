package com.jason;

import com.jason.dto.TokenDTO;
import com.jason.util.ObjectMapperUtil;
import org.junit.Test;

/**
 * @Author Jason
 * @Date 2022/07/18
 */
public class ObjectMapperUtilTest {

    @Test
    public void obj2MapTest() {
        TokenDTO tokenDTO = TokenDTO.builder()
                .token("token")
                .expiredAt(System.currentTimeMillis())
                .issuedAt(System.currentTimeMillis())
                .build();
        System.out.println(ObjectMapperUtil.obj2Map(tokenDTO));
    }
}
