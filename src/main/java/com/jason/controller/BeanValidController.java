package com.jason.controller;


import com.jason.dto.BeanValidationDTO;
import com.jason.dto.RespDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @Author Jason
 * @since 2022-06-09
 */
@RestController
@RequestMapping("/valid")
@Validated
public class BeanValidController {

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

}
