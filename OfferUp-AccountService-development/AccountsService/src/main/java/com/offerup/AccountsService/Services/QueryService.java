package com.offerup.AccountsService.Services;


import com.offerup.AccountsService.dtos.UserDto;
import com.offerup.AccountsService.dtos.UserEntity;
import com.offerup.AccountsService.repos.AccountsDataBase;
import org.bson.types.ObjectId;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class QueryService {

    @Autowired
    private AccountsDataBase accountsDataBase;


    public UserEntity findUserByEmail(String email){
        return accountsDataBase.findByEmail(email);
    }


    public UserEntity findUserByUsername(String username){
        return accountsDataBase.findByUsername(username);
    }

    public UserDto findUserById(String userId){
        ObjectId id = new ObjectId(userId);
        UserEntity userEntity = accountsDataBase.findById(id);


        if (userEntity != null)
        {
            UserDto userDto = new UserDto(userEntity.getId().toString(), userEntity.getEmail(),  "", userEntity.getUsername(),
                    userEntity.getFirstName(), userEntity.getLastName(), userEntity.getAddress());

            return userDto;
        }
        else{
            throw new NoSuchElementException("not found");
        }




    }

    public List<UserEntity> getAllUsers (){

        return accountsDataBase.findAll();
    }

}
