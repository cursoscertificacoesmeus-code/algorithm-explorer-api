package com.algoritmoexplorer.apialgoritmosexplorer.services;

import com.algoritmoexplorer.apialgoritmosexplorer.dto.EdgeDTO;
import com.algoritmoexplorer.apialgoritmosexplorer.dto.GraphAnalysisDTO;
import com.algoritmoexplorer.apialgoritmosexplorer.dto.GraphRequestDTO;
import com.algoritmoexplorer.apialgoritmosexplorer.dto.NodeAnalysisDTO;
import com.algoritmoexplorer.apialgoritmosexplorer.dto.NodeDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GraphService {

    public GraphAnalysisDTO analyzeGraph(GraphRequestDTO graphData) {
        int nodeCount = graphData.getNodes() != null ? graphData.getNodes().size() : 0;
        int edgeCount = graphData.getEdges() != null ? graphData.getEdges().size() : 0;

        Map<String, NodeAnalysisDTO> nodeAnalysisMap = graphData.getNodes().stream()
                .collect(Collectors.toMap(NodeDTO::getId, node -> new NodeAnalysisDTO(node.getId())));

        // Representação do grafo para análise de graus (respeitando a direção)
        Map<String, List<String>> directedAdjList = new HashMap<>();
        for (NodeDTO node : graphData.getNodes()) {
            directedAdjList.put(node.getId(), new ArrayList<>());
        }

        // Representação do grafo para análise de conectividade fraca (ignorando a direção)
        Map<String, List<String>> weakAdjList = new HashMap<>();
        for (NodeDTO node : graphData.getNodes()) {
            weakAdjList.put(node.getId(), new ArrayList<>());
        }


        if (graphData.getEdges() != null) {
            graphData.getEdges().forEach(edge -> {
                NodeAnalysisDTO sourceNode = nodeAnalysisMap.get(edge.getSource());
                NodeAnalysisDTO targetNode = nodeAnalysisMap.get(edge.getTarget());

                boolean isEdgeDirected = graphData.isDirected() || (graphData.isMixed() && edge.getDirected() != null && edge.getDirected());

                if (isEdgeDirected) {
                    // Lógica para aresta direcionada
                    if (sourceNode != null) sourceNode.incrementOutDegree();
                    if (targetNode != null) targetNode.incrementInDegree();
                    directedAdjList.get(edge.getSource()).add(edge.getTarget());
                } else {
                    // Lógica para aresta não direcionada
                    if (sourceNode != null) {
                        sourceNode.incrementInDegree();
                        sourceNode.incrementOutDegree();
                    }
                    if (targetNode != null) {
                        targetNode.incrementInDegree();
                        targetNode.incrementOutDegree();
                    }
                    // Arestas não direcionadas adicionam em ambas as direções para a lista direcionada também
                    directedAdjList.get(edge.getSource()).add(edge.getTarget());
                    directedAdjList.get(edge.getTarget()).add(edge.getSource());
                }

                // Para a conectividade fraca, todas as arestas são tratadas como não direcionadas
                weakAdjList.get(edge.getSource()).add(edge.getTarget());
                weakAdjList.get(edge.getTarget()).add(edge.getSource());
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

        // --- Lógica de Conectividade Fraca ---
        boolean isConnected = false;
        if (nodeCount > 0) {
            Set<String> visited = new HashSet<>();
            String startNode = graphData.getNodes().get(0).getId(); // Começa do primeiro nó
            dfs(startNode, weakAdjList, visited); // Usa a weakAdjList para a conectividade fraca
            isConnected = (visited.size() == nodeCount);
        } else if (nodeCount == 0 && edgeCount == 0) {
            isConnected = true; // Um grafo vazio é considerado conectado
        }
        // --- Fim da Lógica de Conectividade Fraca ---


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
                nodeDetails,
                isConnected
        );
    }

    // Método auxiliar para Busca em Profundidade (DFS)
    private void dfs(String node, Map<String, List<String>> adjList, Set<String> visited) {
        visited.add(node);
        for (String neighbor : adjList.getOrDefault(node, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, adjList, visited);
            }
        }
    }
}
