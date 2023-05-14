package com.group8.appuser;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Customer extends AppUser{
    public Customer(String firstName, String lastName, String email, String password) {
        super(firstName,lastName,email,password);
    }
}
