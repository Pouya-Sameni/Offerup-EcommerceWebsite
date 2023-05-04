package com.offerup.AccountsService.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Document("accounts")
@AllArgsConstructor
public class UserEntity {
    @Id

    private ObjectId id;
    private String email;
    private Password password;
    private String username;
    private String firstName;
    private String lastName;
    private Address address;




}
