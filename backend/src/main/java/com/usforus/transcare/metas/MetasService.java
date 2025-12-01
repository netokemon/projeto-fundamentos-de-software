package com.usforus.transcare.metas;

import com.usforus.transcare.diario.Diario;
import com.usforus.transcare.diario.DiarioRepository;
import com.usforus.transcare.diario.DiarioRequest;
import com.usforus.transcare.diario.DiarioResponse;
import com.usforus.transcare.user.User;
import com.usforus.transcare.user.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class MetasService {

    private final MetasRepository metasRepository;
    private final UserRepository userRepository;

    public MetasService(MetasRepository metasRepository, UserRepository userRepository) {
        this.metasRepository = metasRepository;
        this.userRepository = userRepository;
    }

    public MetasResponse createMetas(MetasRequest metasRequest, String userEmail){

        User paciente = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        Metas metas = new Metas();

        metas.setNome(metasRequest.nome());

        Metas metasSalvas = metasRepository.save(metas);

        return MetasResponse.fromEntity(metasSalvas);

    }

    public List<MetasResponse> getMetas(String userEmail){
        User paciente = userRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        List<Metas> entradasMetas = metasRepository.findByPacienteId(paciente.getId());
        return entradasMetas.stream()
                .map(MetasResponse::fromEntity)
                .collect(Collectors.toList());
    }










}
