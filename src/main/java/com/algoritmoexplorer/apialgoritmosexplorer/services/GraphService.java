package com.algoritmoexplorer.apialgoritmosexplorer.services;

import com.algoritmoexplorer.apialgoritmosexplorer.dto.GraphAnalysisDTO;
import com.algoritmoexplorer.apialgoritmosexplorer.dto.GraphRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class GraphService {

    public GraphAnalysisDTO analyzeGraph(GraphRequestDTO graphData) {
        int nodeCount = graphData.getNodes() != null ? graphData.getNodes().size() : 0;
        int edgeCount = graphData.getEdges() != null ? graphData.getEdges().size() : 0;

        // Lógica para determinar o tipo de grafo
        String graphType;
        if (graphData.isMixed()) {
            graphType = "Mixed";
        } else if (graphData.isDirected()) {
            graphType = "Directed";
        } else {
            graphType = "Undirected";
        }

        String message = String.format(
                "Graph processed successfully. It is a %s graph with %d nodes and %d edges.",
                graphType.toLowerCase(), // ex: "mixed", "directed"
                nodeCount,
                edgeCount
        );

        return new GraphAnalysisDTO(
                nodeCount,
                edgeCount,
                graphData.isWeighted(),
                graphType,
                message
        );
    }
}
