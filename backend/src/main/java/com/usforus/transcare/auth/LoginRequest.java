package com.usforus.transcare.auth;

// DTO para receber os dados de login
public record LoginRequest(
        String email,
        String senha
) {}