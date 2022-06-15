package com.jason.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.dto.PageReqDTO;
import com.jason.dto.RespDTO;
import com.jason.dto.UserListDTO;
import com.jason.entity.User;
import com.jason.enums.ResponseCodeEnum;
import com.jason.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    public RespDTO<User> getUser(Integer id) {
        User user = userService.getById(id);
        if (Objects.isNull(user)) {
            return RespDTO.fail(ResponseCodeEnum.BAD_REQUEST, "cannot found user");
        }
        return RespDTO.success(user);
    }

    @PostMapping("/save")
    public RespDTO<Void> saveUser(@RequestBody List<User> user) {
        userService.saveBatch(user);
        return RespDTO.success(null);
    }

    @PostMapping("/update")
    public RespDTO<User> updateUser(User user) {
        userService.updateById(user);
        return RespDTO.success(userService.getById(user.getId()));
    }

    @GetMapping("/list")
    public RespDTO<UserListDTO> listUser(PageReqDTO pageReqDTO) {
        IPage<User> userIPage = new Page<>(pageReqDTO.getCurrentPage(), pageReqDTO.getPageSize());
        IPage<User> page = userService.page(userIPage);

        UserListDTO userListDTO = UserListDTO.builder()
                .pageNumber(page.getCurrent())
                .totalPage(page.getPages())
                .pageSize(page.getSize())
                .user(page.getRecords())
                .build();
        return RespDTO.success(userListDTO);
    }

}
