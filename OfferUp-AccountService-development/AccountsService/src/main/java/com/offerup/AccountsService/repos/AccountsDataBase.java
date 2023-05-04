package com.offerup.AccountsService.repos;


import com.offerup.AccountsService.dtos.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsDataBase extends MongoRepository<UserEntity, String> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername (String username);
    UserEntity findById (ObjectId id);

}
