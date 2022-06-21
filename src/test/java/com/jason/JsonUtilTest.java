package com.jason;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.jason.dto.RespDTO;
import com.jason.dto.TokenDTO;
import com.jason.entity.User;
import org.junit.Test;

import java.util.List;

public class JsonUtilTest {

    @Test
    public void json2ObjTest() {
        String json = "{\"code\":200,\"msg\":\"Success\"}";
        System.out.println(JSONUtil.toBean(json, RespDTO.class));
    }

    @Test
    public void jsonList2ObjTest() {
        String json = "[{\"id\":1,\"name\":\"Jason_Update\",\"age\":30,\"email\":\"jason.mei.update@xgate.com\",\"createdAt\":null,\"updatedAt\":\"2022-06-09 17:43:48\"},{\"id\":2,\"name\":\"Jason\",\"age\":28,\"email\":\"jason.mei@xgate.com\",\"createdAt\":null,\"updatedAt\":null},{\"id\":3,\"name\":\"Jason_Update\",\"age\":30,\"email\":\"jason.mei.update@xgate.com\",\"createdAt\":\"2022-06-09 17:35:52\",\"updatedAt\":\"2022-06-09 17:44:12\"},{\"id\":4,\"name\":\"Jason\",\"age\":28,\"email\":\"jason.mei@xgate.com\",\"createdAt\":\"2022-06-09 17:37:18\",\"updatedAt\":null},{\"id\":5,\"name\":\"Jason\",\"age\":28,\"email\":\"jason.mei@xgate.com\",\"createdAt\":\"2022-06-09 17:43:22\",\"updatedAt\":null}]";
        List<User> users = JSONUtil.toList(json, User.class);
        System.out.println(users);
    }

    @Test
    public void typeReferrenceTest() {
        TypeReference<RespDTO<TokenDTO>> typeReference = new TypeReference<RespDTO<TokenDTO>>() {
        };

        String json = "{\n" +
                "  \"code\": 200,\n" +
                "  \"msg\": \"Success\",\n" +
                "  \"body\": {\n" +
                "    \"token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJKYXNvbiIsInN1YiI6IntcImFwcElkXCI6XCJKYXNvblwifSIsIm5iZiI6MTY1NTc5NDQwNCwiZXhwIjoxNjU1Nzk1MDA0LCJpYXQiOjE2NTU3OTQ0MDR9.fILa_CZX-sqkR9fNNa98A1XtnwSAEXjsXhO0hByW6_hTlYKH6MqK0sDvMlbr4FHttlM75IMMsJiKF4xsZh1jwg\",\n" +
                "    \"expiredAt\": 1655795005,\n" +
                "    \"issuedAt\": 1655794405\n" +
                "  }\n" +
                "}";
        RespDTO<TokenDTO> respDTO = JSONUtil.toBean(json, typeReference, true);
        System.out.println(respDTO);

    }


}
