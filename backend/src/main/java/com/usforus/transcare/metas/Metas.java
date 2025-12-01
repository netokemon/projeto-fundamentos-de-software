package com.usforus.transcare.metas;

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

    public void setNome(String nome) {
        this.nome = nome;
    }
}
