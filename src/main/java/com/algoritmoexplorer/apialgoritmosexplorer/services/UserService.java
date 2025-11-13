package com.algoritmoexplorer.apialgoritmosexplorer.services;

import com.algoritmoexplorer.apialgoritmosexplorer.dto.UserResponseDTO;
import com.algoritmoexplorer.apialgoritmosexplorer.modal.User;
import com.algoritmoexplorer.apialgoritmosexplorer.modal.UserRole;
import com.algoritmoexplorer.apialgoritmosexplorer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        // Define o role padrão como PERSONAL para novos usuários
        newUser.setRole(UserRole.PERSONAL);

        // Salva o usuário com a senha já criptografada e com o role definido
        return userRepository.save(newUser);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<UserResponseDTO> getPersonalUsers() {
        return userRepository.findByRole(UserRole.PERSONAL).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return convertToDto(user);
    }

    private UserResponseDTO convertToDto(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }
}
