package com.usforus.transcare.metas;

import com.usforus.transcare.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "metas")

public class Metas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    public String getNome() {
        return nome;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User paciente;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }
}
