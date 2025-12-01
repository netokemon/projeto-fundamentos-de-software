package com.usforus.transcare.diario;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.usforus.transcare.consulta.ConsultaResponse;


@RestController
@RequestMapping("/api/diario")
@CrossOrigin(origins = "*")
public class DiarioController {

    private final DiarioService diarioService;

    public DiarioController(DiarioService diarioService){
        this.diarioService = diarioService;
    }

    @PostMapping
    public ResponseEntity<DiarioResponse> criarEntradaDiario(@RequestBody DiarioRequest diarioRequest, @AuthenticationPrincipal UserDetails userDetails){
        String userEmail = userDetails.getUsername();
        DiarioResponse response = diarioService.createEntradaDiario(diarioRequest, userEmail);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/minhas")
    public ResponseEntity<List<DiarioResponse>> getEntradasDiario(@AuthenticationPrincipal UserDetails userDetails){
        String userEmail = userDetails.getUsername();
        List<DiarioResponse> response = diarioService.getMinhasEntradas(userEmail);
        return ResponseEntity.ok(response);
    }
}
