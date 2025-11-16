package com.usforus.transcare.diario;

import java.time.LocalDateTime;

public record DiarioResponse(
    Integer avaliacao,
    String descricao,
    Humor humor,
    LocalDateTime dataHora) 
    
{

    public static DiarioResponse fromEntity(Diario diario) {
        return new DiarioResponse(
            diario.getAvaliacao(),
            diario.getDescricao(),
            diario.getHumor(),
            diario.getDataHora()
        );
    }

}
