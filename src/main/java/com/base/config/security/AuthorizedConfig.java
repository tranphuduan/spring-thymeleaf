package com.base.config.security;

import com.base.config.CacheSystem;
import com.base.config.HttpRequestUtils;
import com.base.dao.entity.PermissionEntity;
import com.base.dao.repository.PermissionRepo;
import com.base.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "authorizedConfig")
@Slf4j
public class AuthorizedConfig {

    @Autowired
    private HttpRequestUtils httpRequestUtils;

    @Autowired
    private CacheSystem cacheSystem;

    @Autowired
    PermissionRepo permissionRepo;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public boolean preAuth(String permission) {
        String token = httpRequestUtils.getAuthToken();
        if (StringUtils.isNullOrEmpty(token)) return false;
        List<String> roleList = jwtTokenUtil.getListRole(token.substring(7,token.length()));
        if (roleList == null || roleList.size() == 0) return false;

        List<PermissionEntity> permissions = getAllPermission();
        return permissions.stream()
                .anyMatch(x -> x.getPermission().equals(permission)
                        && x.getRoleList().contains(roleList.get(0)));
    }

    public List<PermissionEntity> getAllPermission() {
        List<PermissionEntity> result = cacheSystem.getPermissions();
        if (result == null || result.size() > 0) {
            result = permissionRepo.findAll();
        }
        return result;
    }
}
