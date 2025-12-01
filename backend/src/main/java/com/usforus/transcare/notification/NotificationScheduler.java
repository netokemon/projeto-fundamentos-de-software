package com.usforus.transcare.notification;

import com.usforus.transcare.consulta.Consulta;
import com.usforus.transcare.consulta.ConsultaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class NotificationScheduler {

    private final ConsultaRepository consultaRepository;
    private final EmailService emailService;

    public NotificationScheduler(ConsultaRepository consultaRepository, EmailService emailService) {
        this.consultaRepository = consultaRepository;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 * * * *")
    @Transactional
    public void checarConsultasProximas() {
        System.out.println("🔎 Verificando consultas...");

        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime inicioJanela = agora.plusHours(23);
        LocalDateTime fimJanela = agora.plusHours(25);

        List<Consulta> consultasParaAvisar = consultaRepository
                .findByDataHoraBetweenAndNotificacaoEnviadaFalse(inicioJanela, fimJanela);

        for (Consulta c : consultasParaAvisar) {

            emailService.enviarAvisoConsulta(
                    c.getPaciente().getEmail(),
                    c.getPaciente().getNomeCompleto(),
                    c.getNomeMedico(),
                    c.getDataHora().toString()
            );

            c.setNotificacaoEnviada(true);
            consultaRepository.save(c);
        }
    }
}