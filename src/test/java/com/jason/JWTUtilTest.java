package com.jason;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.*;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.jason.entity.User;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JWTUtilTest {

    String signer = "123456";
    long expireAt = 5L;
    String argorithm = "hs512";
    String token = "";
    JWTSigner jwtSigner = JWTSignerUtil.createSigner(argorithm, signer.getBytes());

    @Test
    public void createToken() {
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;
            {
                put(JWTPayload.ISSUED_AT, DateUtil.currentSeconds()); // 签发时间
                put(JWTPayload.NOT_BEFORE, DateUtil.currentSeconds()); // 生效时间
                put(JWTPayload.EXPIRES_AT, DateUtil.currentSeconds() + expireAt); // 失效时间
                put(JWTPayload.AUDIENCE, "Jason");

                User user = User.builder().id(1L).build();
                put(JWTPayload.SUBJECT, JSONUtil.toJsonStr(user));
            }
        };


        token = JWTUtil.createToken(map, jwtSigner);
        System.out.println(token);
    }

    @Test
    public void parseToken() {
        createToken();
        JWT jwt = JWTUtil.parseToken(token);
        System.out.println("------------ header ---------------");
        System.out.println(jwt.getHeader(JWTHeader.ALGORITHM));
        System.out.println(jwt.getHeader(JWTHeader.TYPE));

        System.out.println("------------ payload --------------");
        System.out.println(jwt.getPayload(JWTPayload.AUDIENCE));
        System.out.println(jwt.getPayload(JWTPayload.ISSUED_AT));
        System.out.println(jwt.getPayload(JWTPayload.NOT_BEFORE));
        System.out.println(jwt.getPayload(JWTPayload.EXPIRES_AT));
        System.out.println(jwt.getPayload(JWTPayload.SUBJECT));
    }

    @Test
    public void verifyToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJKYXNvbiIsInN1YiI6IntcImlkXCI6MSxcIm5hbWVcIjpudWxsLFwiYWdlXCI6bnVsbCxcImVtYWlsXCI6bnVsbCxcImNyZWF0ZWRBdFwiOm51bGwsXCJ1cGRhdGVkQXRcIjpudWxsfSIsIm5iZiI6MTY1NTE5NDQyMywiZXhwIjoxNjU1MTk0NDI4LCJpYXQiOjE2NTUxOTQ0MjN9.iNk3VMeFRj7BOjZPB_IUVKgDOZR8EzF1XFlicH1lH5hq1f9qQaXVZucepLRi0Z0MGsyoijZvxdt9ZuWH5Ul5uw";
        //createToken();
        System.out.println("------------ vefify token ---------------");
        System.out.println(JWTUtil.verify(token, jwtSigner));
        System.out.println(JWTValidator.of(token).validateDate());
    }
}
