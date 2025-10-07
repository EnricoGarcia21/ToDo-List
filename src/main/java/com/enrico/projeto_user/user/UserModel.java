package com.enrico.projeto_user.user;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_users")
public class UserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;


    private String name;
    private String password;

    @Column(unique = true)
    private String userName;

    @CreationTimestamp //quando foi gerado
    private LocalDateTime createdAt;
}
