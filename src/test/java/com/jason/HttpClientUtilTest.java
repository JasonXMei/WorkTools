package com.jason;

import cn.hutool.json.JSONUtil;
import com.jason.constant.CommonConstant;
import com.jason.dto.*;
import com.jason.entity.Credential;
import com.jason.entity.User;
import com.jason.util.HttpClientUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientUtilTest {

    String baseUrl = "http://localhost:8080";
    Map<String, String> header = new HashMap<>();

    @Before
    public void init() {
        Credential credential = new Credential();
        credential.setAppId("Jason");
        credential.setAppSecret("123456");
        TokenDTO tokenDTO = HttpClientUtil.postForm(baseUrl + "/credential/createToken", null, credential, TokenDTO.class);
        header.put(CommonConstant.HEADER_KEY, tokenDTO.getToken());
    }


    @Test
    public void getTest() {
        User user = User.builder().id(3L).build();
        System.out.println(HttpClientUtil.get(baseUrl + "/user/get", header, user, User.class));
    }

    @Test
    public void listTest() {
        PageReqDTO pageReqDTO = PageReqDTO.builder()
                .currentPage(1L)
                .pageSize(10L)
                .build();

        System.out.println(HttpClientUtil.get(baseUrl + "/user/list", header, pageReqDTO, UserListDTO.class));
    }

    @Test
    public void saveTest() {
        List<User> users = new ArrayList<>();
        users.add(User.builder().name("Jason").age(28).email("jason.mei@xgate.com").build());
        users.add(User.builder().name("Jason").age(28).email("jason.mei@xgate.com").build());

        System.out.println(HttpClientUtil.postJson(baseUrl + "/user/save", header, JSONUtil.toJsonStr(users), BaseDTO.class));
    }

    @Test
    public void updateTest() {
        User user = User.builder().name("Jason").age(28).email("jason.mei@xgate.com").id(3L).build();
        System.out.println(HttpClientUtil.postForm(baseUrl + "/user/update", header, user, User.class));
    }

}
