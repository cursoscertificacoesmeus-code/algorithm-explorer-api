package com.algoritmoexplorer.apialgoritmosexplorer.services;

import com.algoritmoexplorer.apialgoritmosexplorer.modal.User;
import com.algoritmoexplorer.apialgoritmosexplorer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User newUser) {
        // Criptografa a senha antes de salvar
        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);

        // Salva o usuário com a senha já criptografada
        return userRepository.save(newUser);
    }
}
