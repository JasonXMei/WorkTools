package com.jason.dto;

import com.jason.entity.User;
import lombok.*;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserListDTO {

    private Long pageNumber;
    private Long pageSize;
    private Long totalPage;
    private List<User> user;

}
