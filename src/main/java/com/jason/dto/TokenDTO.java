package com.jason.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @Author Jason
 * @Date 2022/06/21
 */
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class TokenDTO {

    private String token;
    private Long expiredAt;
    private Long issuedAt;

}
