package com.usforus.transcare.metas;


import com.usforus.transcare.diario.DiarioRequest;
import com.usforus.transcare.diario.DiarioResponse;
import com.usforus.transcare.diario.DiarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metas")
public class MetasController {

    private final MetasService metasService;

    public MetasController(MetasService metasService){
        this.metasService = metasService;
    }

    @PostMapping
    public ResponseEntity<MetasResponse> criarMetas(@RequestBody MetasRequest metasRequest, @AuthenticationPrincipal UserDetails userDetails){
        String userEmail = userDetails.getUsername();
        MetasResponse response = metasService.createMetas(metasRequest, userEmail);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/minhas")
    public ResponseEntity<List<MetasResponse>> getMetas(@AuthenticationPrincipal UserDetails userDetails){
        String userEmail = userDetails.getUsername();
        List<MetasResponse> response = metasService.getMetas(userEmail);
        return ResponseEntity.ok(response);
    }

}
