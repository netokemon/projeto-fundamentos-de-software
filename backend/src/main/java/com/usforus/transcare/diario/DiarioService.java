package com.usforus.transcare.diario;

import com.usforus.transcare.user.User;
import com.usforus.transcare.user.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class DiarioService {

    private final DiarioRepository diarioRepository;
    private final UserRepository userRepository;

    public DiarioService(DiarioRepository diarioRepository, UserRepository userRepository) {
        this.diarioRepository = diarioRepository;
        this.userRepository = userRepository;
    }

    public DiarioResponse createEntradaDiario(DiarioRequest diarioRequest, String userEmail){

        User paciente = userRepository.findByEmail(userEmail)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        
        Diario diario = new Diario();

        diario.setAvaliacao(diarioRequest.avaliacao());
        diario.setDataHora(diarioRequest.dataHora());
        diario.setDescricao(diarioRequest.descricao());
        diario.setHumor(diarioRequest.humor());
        diario.setPaciente(paciente);

        Diario diarioSalvo = diarioRepository.save(diario);

        return DiarioResponse.fromEntity(diarioSalvo);

    }

    public List<DiarioResponse> getMinhasEntradas(String userEmail){
        User paciente = userRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        List<Diario> entradasDiario = diarioRepository.findByPacienteId(paciente.getId());
        return entradasDiario.stream()
                .map(DiarioResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
