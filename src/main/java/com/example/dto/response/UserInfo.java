package com.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserInfo {
    private String username;
    private String alias;
    private String role;
}
