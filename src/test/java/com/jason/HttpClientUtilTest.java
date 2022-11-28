package com.jason;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.jason.constant.CommonConstant;
import com.jason.dto.PageReqDTO;
import com.jason.dto.RespDTO;
import com.jason.dto.TokenDTO;
import com.jason.dto.UserListDTO;
import com.jason.entity.Credential;
import com.jason.entity.User;
import com.jason.util.HttpClientUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HttpClientUtilTest {

    String baseUrl = "http://localhost:8080";
    Map<String, String> header = new HashMap<>();

    @Before
    public void init() {
        Credential credential = new Credential();
        credential.setAppId("Jason");
        credential.setAppSecret("123456");
        RespDTO<TokenDTO> tokenDTO = HttpClientUtil.postForm(baseUrl + "/credential/createToken", null, credential, new TypeReference<RespDTO<TokenDTO>>() {
        });
        header.put(CommonConstant.HEADER_KEY, tokenDTO.getBody().getToken());
    }

    @Test
    public void testTransaction() {
        HttpClientUtil.get(baseUrl + "/user/testTransaction", header, null, new TypeReference<RespDTO<User>>() {
        });
    }

    @Test
    public void addAgeTest() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i=0;i<100;i++){
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    HttpClientUtil.get(baseUrl + "/user/addAge", header, null, new TypeReference<RespDTO<User>>() {
                    });
                }
            });
        }

        threadPool.shutdown();
        System.out.println("pool shutdown: " + threadPool.isShutdown());
        while (!threadPool.isTerminated()) {
            threadPool.awaitTermination(1, TimeUnit.SECONDS);
        }
        System.out.println("all task complete");
    }


    @Test
    public void getTest() {
        User user = User.builder().id(3L).build();
        System.out.println(HttpClientUtil.get(baseUrl + "/user/get", header, user, new TypeReference<RespDTO<User>>() {
        }));
    }

    @Test
    public void listTest() {
        PageReqDTO pageReqDTO = PageReqDTO.builder()
                .currentPage(1L)
                .pageSize(10L)
                .build();

        System.out.println(HttpClientUtil.get(baseUrl + "/user/list", header, pageReqDTO, new TypeReference<RespDTO<UserListDTO>>() {
        }));
    }

    @Test
    public void saveTest() {
        List<User> users = new ArrayList<>();
        users.add(User.builder().name("Jason").age(28).email("jason.mei@xgate.com").build());
        users.add(User.builder().name("Jason").age(28).email("jason.mei@xgate.com").build());

        System.out.println(HttpClientUtil.postJson(baseUrl + "/user/save", header, JSONUtil.toJsonStr(users), new TypeReference<RespDTO<User>>() {
        }));
    }

    @Test
    public void updateTest() {
        User user = User.builder().name("Jason").age(28).email("jason.mei@xgate.com").id(3L).build();
        System.out.println(HttpClientUtil.postForm(baseUrl + "/user/update", header, user, new TypeReference<RespDTO<User>>() {
        }));
    }

}
