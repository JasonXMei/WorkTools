package com.jason.dto;

import com.jason.entity.User;
import lombok.*;

import java.util.List;

/**
 * @Author Jason
 * @Date 2022/06/21
 */
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
