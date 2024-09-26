package com.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HttpRequestUtils {

    @Autowired
    private HttpServletRequest request;

    public String getAuthToken() {
        return request.getHeader("Authorization");
    }
}
