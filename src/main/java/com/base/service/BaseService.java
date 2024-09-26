package com.base.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BaseService {

    @Value("${aes.secret}")
    private String aesSecret;
}