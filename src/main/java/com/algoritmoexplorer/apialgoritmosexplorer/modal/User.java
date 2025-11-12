package com.algoritmoexplorer.apialgoritmosexplorer.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; // Será armazenada como hash

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING) // Armazena o enum como String no banco de dados
    @Column(nullable = false) // Garante que o role não seja nulo
    private UserRole role; // Usando o enum UserRole
}
