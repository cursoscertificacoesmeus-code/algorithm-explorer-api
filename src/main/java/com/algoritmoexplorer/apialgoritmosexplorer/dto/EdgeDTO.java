package com.algoritmoexplorer.apialgoritmosexplorer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EdgeDTO {
    private String source;
    private String target;
    private Double weight; // Usando Double para permitir valores nulos
    private Boolean directed; // Usando Boolean para permitir valores nulos/undefined
}
