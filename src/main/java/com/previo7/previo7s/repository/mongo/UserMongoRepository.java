package com.previo7.previo7s.repository.mongo;

import com.previo7.previo7s.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
