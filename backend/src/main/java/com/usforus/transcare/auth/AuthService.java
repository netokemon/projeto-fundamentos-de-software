package com.usforus.transcare.auth;

import com.usforus.transcare.user.User;
import com.usforus.transcare.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Injeção de dependências via construtor
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request) {
        // 1. Verifica se o email já está em uso
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        // 2. CRIPTOGRAFA a senha antes de salvar
        String senhaHashed = passwordEncoder.encode(request.senha());

        // 3. Cria o novo usuário
        User novoUsuario = new User(
                request.nomeCompleto(),
                request.email(),
                senhaHashed // Salva a senha criptografada!
        );

        // 4. Salva no banco de dados
        return userRepository.save(novoUsuario);
    }
}
