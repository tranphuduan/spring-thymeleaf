package com.base.dao.repository;


import com.base.dao.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByUsernameOrAlias(String username,String alias);

    boolean existsByUsername(String username);

    List<UserEntity> findAllByAlias(String alias);
}
