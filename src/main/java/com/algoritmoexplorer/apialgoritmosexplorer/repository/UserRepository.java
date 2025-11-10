package com.algoritmoexplorer.apialgoritmosexplorer.repository;

import com.algoritmoexplorer.apialgoritmosexplorer.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JpaRepository<Entidade, Tipo_da_Chave_Primaria>
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}