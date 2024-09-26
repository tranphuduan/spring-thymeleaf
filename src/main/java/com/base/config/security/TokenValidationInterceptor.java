package com.base.config.security;

import com.base.dao.entity.TokenEntity;
import com.base.enums.ErrorSystem;
import com.base.exception.BusinessException;
import com.base.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenValidationInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenUtil tokenUtil;
    @Autowired
    private MongoTemplate mongoTemplate;

    public TokenEntity checkExistToken(String token, String username) {
        Query query = new Query();

        // Tạo Criteria cho điều kiện token
        Criteria tokenCriteria = Criteria.where("token").is(token);

        // Tạo Criteria cho điều kiện username hoặc alias
        Criteria usernameCriteria = new Criteria().orOperator(
                Criteria.where("username").is(username),
                Criteria.where("alias").is(username)
        );

        // Kết hợp hai Criteria lại với nhau
        query.addCriteria(tokenCriteria.andOperator(usernameCriteria));

        // Tìm và trả về kết quả
        return mongoTemplate.findOne(query, TokenEntity.class);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("Authorization"); // Hoặc phương thức lấy token khác
        String jwtToken = token.substring(7);
        String username = tokenUtil.getUsernameFromToken(jwtToken);
        TokenEntity checkToken = checkExistToken(jwtToken, username);
        if (checkToken == null || StringUtils.isNullOrEmpty(checkToken.getToken())) {
            throw new BusinessException(ErrorSystem.TOKEN_INCORRECT);
        }
        return true;
    }
}
