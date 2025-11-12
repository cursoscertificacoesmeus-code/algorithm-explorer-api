package com.algoritmoexplorer.apialgoritmosexplorer.dto;

import com.algoritmoexplorer.apialgoritmosexplorer.modal.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private UserRole role;
}
