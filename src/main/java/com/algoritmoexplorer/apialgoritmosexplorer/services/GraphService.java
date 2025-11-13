package com.algoritmoexplorer.apialgoritmosexplorer.services;

import com.algoritmoexplorer.apialgoritmosexplorer.dto.GraphAnalysisDTO;
import com.algoritmoexplorer.apialgoritmosexplorer.dto.GraphRequestDTO;
import com.algoritmoexplorer.apialgoritmosexplorer.dto.NodeAnalysisDTO;
import com.algoritmoexplorer.apialgoritmosexplorer.dto.NodeDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GraphService {

    public GraphAnalysisDTO analyzeGraph(GraphRequestDTO graphData) {
        int nodeCount = graphData.getNodes() != null ? graphData.getNodes().size() : 0;
        int edgeCount = graphData.getEdges() != null ? graphData.getEdges().size() : 0;

        Map<String, NodeAnalysisDTO> nodeAnalysisMap = graphData.getNodes().stream()
                .collect(Collectors.toMap(NodeDTO::getId, node -> new NodeAnalysisDTO(node.getId())));

        if (graphData.getEdges() != null) {
            graphData.getEdges().forEach(edge -> {
                NodeAnalysisDTO sourceNode = nodeAnalysisMap.get(edge.getSource());
                NodeAnalysisDTO targetNode = nodeAnalysisMap.get(edge.getTarget());

                boolean isEdgeDirected = graphData.isDirected() || (graphData.isMixed() && edge.getDirected() != null && edge.getDirected());

                if (isEdgeDirected) {
                    // Lógica para aresta direcionada
                    if (sourceNode != null) sourceNode.incrementOutDegree();
                    if (targetNode != null) targetNode.incrementInDegree();
                } else {
                    // Lógica para aresta não direcionada
                    // Em um grafo não direcionado, o grau de entrada e saída são frequentemente considerados o mesmo ("grau").
                    // Vamos incrementar ambos para representar a conexão.
                    if (sourceNode != null) {
                        sourceNode.incrementInDegree();
                        sourceNode.incrementOutDegree();
                    }
                    if (targetNode != null) {
                        targetNode.incrementInDegree();
                        targetNode.incrementOutDegree();
                    }
                }
            });
        }

        List<NodeAnalysisDTO> nodeDetails = new ArrayList<>(nodeAnalysisMap.values());

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
                graphType.toLowerCase(),
                nodeCount,
                edgeCount
        );

        return new GraphAnalysisDTO(
                nodeCount,
                edgeCount,
                graphData.isWeighted(),
                graphType,
                message,
                nodeDetails
        );
    }
}
