package com.usforus.transcare.auth;

import com.usforus.transcare.config.JwtService;
import com.usforus.transcare.user.User;
import com.usforus.transcare.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public User register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        String senhaHashed = passwordEncoder.encode(request.senha());
        User novoUsuario = new User(
                request.nomeCompleto(),
                request.email(),
                senhaHashed
        );
        novoUsuario.setNomeSocial(request.nomeSocial());
        novoUsuario.setPronomes(request.pronomes());
        novoUsuario.setDataNascimento(request.dataNascimento());
        novoUsuario.setIdentidadeGenero(request.identidadeGenero());
        novoUsuario.setEtapaTransicao(request.etapaTransicao());
        novoUsuario.setRegiao(request.regiao());
        return userRepository.save(novoUsuario);
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.senha()
                )
        );

        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalStateException("Usuário não encontrado após autenticação"));

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getSenha())
                .authorities(java.util.Collections.emptyList())
                .build();

        String jwtToken = jwtService.generateToken(userDetails);

        return new LoginResponse(jwtToken);
    }
}