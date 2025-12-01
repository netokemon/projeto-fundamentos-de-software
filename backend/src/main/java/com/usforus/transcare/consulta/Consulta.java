package com.usforus.transcare.consulta;

import com.usforus.transcare.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeMedico;

    @Column(nullable = false)
    private String local;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Especializacao especializacao;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User paciente;

    @Column(nullable = false)
    private boolean notificacaoEnviada = false; // Padrão é falso

    public boolean isNotificacaoEnviada() {
        return notificacaoEnviada;
    }


    public Consulta() {
    }

    public Long getId() {
        return id;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public String getLocal() {
        return local;
    }

    public Especializacao getEspecializacao() {
        return especializacao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public User getPaciente() {
        return paciente;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setEspecializacao(Especializacao especializacao) {
        this.especializacao = especializacao;
    }

    public void setPaciente(User paciente) {
        this.paciente = paciente;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public void setNotificacaoEnviada(boolean notificacaoEnviada) {
        this.notificacaoEnviada = notificacaoEnviada;
    }
}