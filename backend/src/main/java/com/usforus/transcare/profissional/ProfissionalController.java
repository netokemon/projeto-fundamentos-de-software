package com.usforus.transcare.profissional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @GetMapping
    public ResponseEntity<List<Profissional>> buscarProfissionais(
            @RequestParam(required = false) String especializacao
    ) {
        List<Profissional> resultado = profissionalService.buscar(especializacao);
        return ResponseEntity.ok(resultado);
    }
}
