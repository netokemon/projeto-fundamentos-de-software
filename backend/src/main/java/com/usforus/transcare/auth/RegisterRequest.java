package com.usforus.transcare.auth;

public record RegisterRequest(
        String nomeCompleto,
        String email,
        String senha
) {}
