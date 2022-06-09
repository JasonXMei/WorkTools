package com.jason;

import com.jason.entity.User;
import com.jason.util.HttpClientUtil;
import com.jason.util.JsonUtil;
import org.junit.jupiter.api.Test;

public class HttpClientUtilTest {

    String baseUrl = "http://localhost:8080/user";

    @Test
    public void getTest() {
        User user = new User();
        user.setId(3L);

        User resp = HttpClientUtil.get(baseUrl + "/get", null, user, User.class);
        System.out.println(resp);
    }

    @Test
    public void saveTest() {
        User user = new User();
        user.setName("Jason");
        user.setAge(28);
        user.setEmail("jason.mei@xgate.com");

        User resp = HttpClientUtil.postForm(baseUrl + "/save", null, user, User.class);
        System.out.println(resp);
    }

    @Test
    public void updateTest() {
        User user = new User();
        user.setName("Jason_Update");
        user.setAge(30);
        user.setEmail("jason.mei.update@xgate.com");
        user.setId(3L);

        User resp = HttpClientUtil.postJson(baseUrl + "/update", null, JsonUtil.toJson(user), User.class);
        System.out.println(resp);
    }

}
