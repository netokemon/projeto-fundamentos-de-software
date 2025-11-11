package com.usforus.transcare.auth;

import com.usforus.transcare.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth") // Todas as rotas aqui começarão com /auth
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        try {
            authService.register(request);
            return ResponseEntity.ok("Usuário cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            // Se o email já existir (lançado pelo service)
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Outros erros
            return ResponseEntity.status(500).body("Erro interno no servidor.");
        }
    }
}
