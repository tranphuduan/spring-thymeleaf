package com.base.dao.repository;


import com.base.dao.entity.PhotoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepo extends MongoRepository<PhotoEntity, String> {
}
