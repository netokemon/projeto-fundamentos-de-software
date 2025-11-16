package com.usforus.transcare.consulta;

import java.time.LocalDateTime;

// DTO para CRIAR uma nova consulta
public record ConsultaRequest(
        String nomeMedico,
        String local,
        Especializacao especializacao,
        LocalDateTime dataHora
) {}