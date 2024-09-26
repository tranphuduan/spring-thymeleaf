package com.base.service;

import com.base.config.security.JwtTokenUtil;
import com.base.dao.entity.UserInfoEntity;
import com.base.dao.repository.UserInfoRepo;
import com.base.enums.ErrorSystem;
import com.base.model.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@Slf4j
public class UserService extends BaseService{
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserInfoRepo userInfoRepo;

    public BaseResponse<UserInfoEntity> loadUserInfo(HttpServletRequest request){
        String requestTokenHeader = request.getHeader("Authorization");
        requestTokenHeader.startsWith("Bearer ");

        String jwtToken = requestTokenHeader.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(jwtToken);

        Optional<UserInfoEntity> userInfo = userInfoRepo.findByUsername(username);
        UserInfoEntity result = userInfo.get();
        result.decrypt();
        return new BaseResponse<>(ErrorSystem.SUCCESS,result);
    }
}