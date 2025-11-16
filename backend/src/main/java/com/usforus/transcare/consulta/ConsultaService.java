package com.usforus.transcare.consulta;

import com.usforus.transcare.user.User;
import com.usforus.transcare.user.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final UserRepository userRepository;

    public ConsultaService(ConsultaRepository consultaRepository, UserRepository userRepository) {
        this.consultaRepository = consultaRepository;
        this.userRepository = userRepository;
    }


    public ConsultaResponse createConsulta(ConsultaRequest request, String userEmail) {
        User paciente = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        Consulta novaConsulta = new Consulta();
        novaConsulta.setNomeMedico(request.nomeMedico());
        novaConsulta.setLocal(request.local());
        novaConsulta.setEspecializacao(request.especializacao());
        novaConsulta.setDataHora(request.dataHora());
        novaConsulta.setPaciente(paciente); // Many to one (varias consultas pra um usuario)

        Consulta consultaSalva = consultaRepository.save(novaConsulta);

        return ConsultaResponse.fromEntity(consultaSalva);
    }


    public List<ConsultaResponse> getMinhasConsultas(String userEmail) {
        User paciente = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        List<Consulta> consultas = consultaRepository.findByPacienteId(paciente.getId());

        return consultas.stream()
                .map(ConsultaResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
