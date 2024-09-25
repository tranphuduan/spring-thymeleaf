package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TẠo ra class này chỉ để lưu giữ thông tin
 */
@Data
@AllArgsConstructor
public class TokenInfo {
    String username;
    String fullName;
    String token;
    String expTime;
}
