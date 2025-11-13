package com.algoritmoexplorer.apialgoritmosexplorer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphAnalysisDTO {
    private int nodeCount;
    private int edgeCount;
    private boolean isWeighted;
    private String graphType;
    private String message;
    private List<NodeAnalysisDTO> nodeDetails; // Novo campo para detalhes dos nós
}
