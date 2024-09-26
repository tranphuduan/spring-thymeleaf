package com.base.utils;

import java.util.Arrays;
import java.util.List;

public class AppConstant {

    public static final class Cache {
        private Cache() {
        }

        public static final String cache_permission = "cache_permission";

        public static final List<String> listCache = Arrays.asList(
                Cache.cache_permission);

    }

    public static final class Role {
        private Role() {
        }

        public static final String ADMIN = "ADMIN";
        public static final String USER = "USER";

    }

    public static final class Regex {
        private Regex() {
        }

        public static final String email = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        public static final String password = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    }
}
