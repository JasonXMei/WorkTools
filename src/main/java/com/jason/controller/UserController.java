package com.jason.controller;


import com.jason.entity.User;
import com.jason.service.UserService;
import com.jason.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jason
 * @since 2022-06-09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public String getUser(Integer id) {
        return JsonUtil.toJson(userService.getById(id));
    }

    @PostMapping("/save")
    public String saveUser(User user) {
        userService.save(user);
        return JsonUtil.toJson(user);
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody User user) {
        userService.updateById(user);
        return JsonUtil.toJson(user);
    }

}
