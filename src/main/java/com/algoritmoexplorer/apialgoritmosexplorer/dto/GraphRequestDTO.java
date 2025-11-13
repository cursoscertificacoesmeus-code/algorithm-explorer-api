package com.algoritmoexplorer.apialgoritmosexplorer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GraphRequestDTO {
    private List<NodeDTO> nodes;
    private List<EdgeDTO> edges;
    private boolean directed;
    private boolean weighted;
    private boolean mixed;
}
