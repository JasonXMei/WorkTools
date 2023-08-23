package com.jason;

import com.jason.dto.TokenDTO;
import com.jason.util.ObjectUtil;
import org.junit.Test;

/**
 * @Author Jason
 * @Date 2022/07/18
 */
public class ObjectUtilTest {

    @Test
    public void obj2MapTest() {
        TokenDTO tokenDTO = TokenDTO.builder()
                .token("token")
                .expiredAt(System.currentTimeMillis())
                .build();
        System.out.println(ObjectUtil.obj2Map(tokenDTO));
    }
}
