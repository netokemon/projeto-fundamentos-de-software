package com.usforus.transcare.diario;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiarioRepository extends JpaRepository<Diario, Long> {

    List<Diario> findByPacienteId(Long pacienteId);
}

