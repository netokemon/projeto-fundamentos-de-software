package com.usforus.transcare.metas;

import com.usforus.transcare.diario.Diario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetasRepository extends JpaRepository<Metas, Long>{

    List<Metas> findByPacienteId(Long pacienteId);

}
