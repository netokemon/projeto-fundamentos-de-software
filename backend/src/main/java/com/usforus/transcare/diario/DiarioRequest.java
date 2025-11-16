package com.usforus.transcare.diario;

import java.time.LocalDateTime;

public record DiarioRequest(
    Integer avaliacao,
    String descricao,
    Humor humor,
    LocalDateTime dataHora) {}
