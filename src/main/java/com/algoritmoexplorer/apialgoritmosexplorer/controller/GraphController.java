package com.algoritmoexplorer.apialgoritmosexplorer.controller;

import com.algoritmoexplorer.apialgoritmosexplorer.dto.GraphAnalysisDTO;
import com.algoritmoexplorer.apialgoritmosexplorer.dto.GraphRequestDTO;
import com.algoritmoexplorer.apialgoritmosexplorer.services.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/graph")
public class GraphController {

    private final GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }



    @PostMapping("/process")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GraphAnalysisDTO> processGraph(@RequestBody GraphRequestDTO graphData) {
        GraphAnalysisDTO analysisResult = graphService.analyzeGraph(graphData);
        return ResponseEntity.ok(analysisResult);
    }
}
