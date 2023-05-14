package com.group8.event;


import com.group8.appuser.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "app_user_id")
    private AppUser appUser;
    private String name;
    private LocalDate start;
    private LocalDate end;
    private String location;
    @Column(nullable = false)
    private String status = "Pending";
}

