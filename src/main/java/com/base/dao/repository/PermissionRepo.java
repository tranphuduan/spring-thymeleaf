package com.base.dao.repository;


import com.base.dao.entity.PermissionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissionRepo extends MongoRepository<PermissionEntity, String> {
}
