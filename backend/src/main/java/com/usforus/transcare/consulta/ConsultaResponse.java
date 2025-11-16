package com.usforus.transcare.consulta;

import java.time.LocalDateTime;

public record ConsultaResponse(
        Long idConsulta,
        String nomeMedico,
        String local,
        String especializacao,
        LocalDateTime dataHora,
        String nomePaciente
) {

    public static ConsultaResponse fromEntity(Consulta consulta) {
        return new ConsultaResponse(
                consulta.getId(),
                consulta.getNomeMedico(),
                consulta.getLocal(),
                consulta.getEspecializacao().name(),
                consulta.getDataHora(),
                consulta.getPaciente().getNomeCompleto()
        );
    }
}
