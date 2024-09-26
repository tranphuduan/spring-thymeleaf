package com.base.dao.repository;


import com.base.dao.entity.TokenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TokenRepo extends MongoRepository<TokenEntity, String> {
    Optional<TokenEntity> findByUsernameOrAlias(String username, String alias);
}
