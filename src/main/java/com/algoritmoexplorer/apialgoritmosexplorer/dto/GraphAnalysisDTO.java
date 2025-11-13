package com.algoritmoexplorer.apialgoritmosexplorer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphAnalysisDTO {
    private int nodeCount;
    private int edgeCount;
    private boolean isWeighted;
    private String graphType; // Substitui isDirected e isMixed
    private String message;
}
