package com.usforus.transcare.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByPacienteId(Long pacienteId);

    List<Consulta> findByDataHoraBetweenAndNotificacaoEnviadaFalse(
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
