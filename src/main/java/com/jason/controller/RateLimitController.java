package com.jason.controller;

import com.jason.dto.RespDTO;
import com.jason.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @Author Jason
 * @since 2022-06-09
 */
@RestController
@RequestMapping("/rate")
@Validated
@Slf4j
public class RateLimitController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public RespDTO<String> rateTest(String id) {
        return RespDTO.success(id);
    }

}
