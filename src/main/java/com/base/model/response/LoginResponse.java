package com.base.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * TẠo ra class này chỉ để lưu giữ thông tin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginResponse {
    String username;
    String fullName;
    String token;
    String expTime;
}
