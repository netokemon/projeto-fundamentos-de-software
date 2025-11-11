package com.usforus.transcare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Bean para criptografar e verificar senhas.
     * Usamos o BCrypt, que é o padrão da indústria.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * O "Filtro de Segurança" principal da aplicação.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita o CSRF, comum em APIs REST que usam tokens
                .csrf(AbstractHttpConfigurer::disable)

                // Define as regras de autorização
                .authorizeHttpRequests(auth -> auth
                        // Permite o acesso PÚBLICO ao endpoint de registro
                        .requestMatchers("/auth/register").permitAll()

                        // Exige autenticação para qualquer outra requisição
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}