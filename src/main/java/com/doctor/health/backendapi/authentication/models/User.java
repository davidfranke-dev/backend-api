package com.doctor.health.backendapi.authentication.models;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Document(collection = "users")
@Data
public class User {
    
    @Id
    private String id;

    @Email
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(max = 50)
    private String password;

    @DBRef
    private Set<Role> roles;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
      }

}
