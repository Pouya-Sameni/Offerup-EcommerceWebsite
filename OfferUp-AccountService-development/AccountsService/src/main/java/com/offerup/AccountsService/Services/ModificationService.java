package com.offerup.AccountsService.Services;


import com.offerup.AccountsService.dtos.UserEntity;
import com.offerup.AccountsService.repos.AccountsDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModificationService {

    @Autowired
    private AccountsDataBase accountsDataBase;


    public void insertUser (UserEntity userEntity){
        accountsDataBase.insert(userEntity);
    }
}
