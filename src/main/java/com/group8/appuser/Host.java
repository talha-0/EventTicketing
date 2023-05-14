package com.group8.appuser;

import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@Entity
public class Host extends AppUser {
    public Host(String firstName, String lastName, String email, String password) {
        super(firstName,lastName,email,password);
    }
}