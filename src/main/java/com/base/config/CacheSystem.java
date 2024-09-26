package com.base.config;//package com.epay.service.paymentgateway.cache;

import com.base.dao.entity.PermissionEntity;
import com.base.dao.repository.PermissionRepo;
import com.base.utils.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@EnableCaching
@EnableScheduling
public class CacheSystem {
    private final String timeScheduled = "1800000";

    @Autowired
    CacheManager cacheManager;

    @Autowired
    PermissionRepo permissionRepo;

    @Cacheable(value = AppConstant.Cache.cache_permission, key = "'" + AppConstant.Cache.cache_permission + "'")
    public List<PermissionEntity> getPermissions() {
        List<PermissionEntity> pmsList = permissionRepo.findAll();
        log.info("load cache {} success", AppConstant.Cache.cache_permission);
        return pmsList;
    }

    @Scheduled(fixedRateString = timeScheduled)
    public void cleanPermissions() {
        Cache cache = cacheManager.getCache(AppConstant.Cache.cache_permission);
        if (cache != null) {
            cache.clear();
            log.info("Clean cache {} successfully", AppConstant.Cache.cache_permission);
        }
    }
}
