package com.jason.controller;

import com.jason.dto.BeanValidationDTO;
import com.jason.dto.RespDTO;
import com.jason.entity.User;
import com.jason.service.UserService;
import com.jason.validation.NotConflictUser;
import com.jason.validation.UniqueUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @Author Jason
 * @since 2022-06-09
 */
@RestController
@RequestMapping("/valid")
@Validated
@Slf4j
public class BeanValidController {

    @Autowired
    private UserService userService;

    @PostMapping("/json")
    public RespDTO<BeanValidationDTO> validJson(@RequestBody @Valid BeanValidationDTO beanValidationDTO) {
        return RespDTO.success(beanValidationDTO);
    }

    @PostMapping("/form")
    public RespDTO<BeanValidationDTO> validOne(@Valid BeanValidationDTO beanValidationDTO) {
        return RespDTO.success(beanValidationDTO);
    }

    @PostMapping("/list")
    public RespDTO<Void> validList(@RequestBody @Valid List<BeanValidationDTO> user) {
        return RespDTO.success(null);
    }

    @PostMapping("/saveUser")
    public RespDTO<User> createUser(@UniqueUser @Valid @RequestBody User user) {
        userService.save(user);
        log.info("save user id is {}", user.getId());
        return RespDTO.success(user);
    }

    @PutMapping("/updateUser")
    public RespDTO<User> updateUser(@NotConflictUser @Valid @RequestBody User user) {
        userService.updateById(user);
        log.info("update user is {}", user);
        return RespDTO.success(user);
    }

}
