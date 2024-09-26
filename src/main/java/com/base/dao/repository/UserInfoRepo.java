package com.base.dao.repository;


import com.base.dao.entity.UserInfoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserInfoRepo extends MongoRepository<UserInfoEntity, String> {
    Optional<UserInfoEntity> findByUsername(String username);

}
