package com.base.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringUtils {
    public static boolean notNullOrEmpty(String str) {
        return str != null && !str.trim().equals("");
    }
    public static boolean isNullOrEmpty(String str) {
        return str == null ||  str.trim().equals("");
    }
}
