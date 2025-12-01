package com.usforus.transcare.auth;

import java.time.LocalDate;

public record RegisterRequest(
        String nomeCompleto,
        String email,
        String senha,
        String nomeSocial,
        String pronomes,
        LocalDate dataNascimento,
        String identidadeGenero,
        String etapaTransicao,
        String regiao
) {}
