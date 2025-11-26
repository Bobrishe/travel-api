package com.alexki.authapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;


    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRoles role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProcessingLog> processingLogs;

}
