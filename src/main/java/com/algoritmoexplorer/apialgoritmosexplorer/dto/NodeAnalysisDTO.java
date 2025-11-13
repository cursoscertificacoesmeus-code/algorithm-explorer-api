package com.algoritmoexplorer.apialgoritmosexplorer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NodeAnalysisDTO {
    private String id;
    private int inDegree = 0;
    private int outDegree = 0;

    public NodeAnalysisDTO(String id) {
        this.id = id;
    }

    public void incrementInDegree() {
        this.inDegree++;
    }

    public void incrementOutDegree() {
        this.outDegree++;
    }
}
