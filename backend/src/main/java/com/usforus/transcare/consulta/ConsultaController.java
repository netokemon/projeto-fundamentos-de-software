package com.usforus.transcare.consulta;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas") // Rota base para consultas
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    //Endpoint da API para postar novas consultas no BD
    @PostMapping
    public ResponseEntity<ConsultaResponse> createConsulta(@RequestBody ConsultaRequest request, @AuthenticationPrincipal UserDetails userDetails) 
    {
        String userEmail = userDetails.getUsername();
        ConsultaResponse response = consultaService.createConsulta(request, userEmail);
        return ResponseEntity.ok(response);
    }

    //Endpoint GET da API que retorna a lista das consultas
    @GetMapping("/minhas")
    public ResponseEntity<List<ConsultaResponse>> getMinhasConsultas(@AuthenticationPrincipal UserDetails userDetails) 
    {
        String userEmail = userDetails.getUsername();
        List<ConsultaResponse> consultas = consultaService.getMinhasConsultas(userEmail);
        return ResponseEntity.ok(consultas);
    }
}
