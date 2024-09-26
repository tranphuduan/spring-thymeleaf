package com.base.controller;

import com.base.dao.entity.UserInfoEntity;
import com.base.model.response.BaseResponse;
import com.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/load-info", method = RequestMethod.GET)
    public ResponseEntity<?> loadInfo(HttpServletRequest request) {
        BaseResponse<UserInfoEntity> result = userService.loadUserInfo(request);
        return ResponseEntity.ok(result);
    }


}
