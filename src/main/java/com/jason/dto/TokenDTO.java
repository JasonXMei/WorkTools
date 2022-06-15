package com.jason.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class TokenDTO {

    private String token;
    private Long expiredAt;
    private Long issuedAt;

}
