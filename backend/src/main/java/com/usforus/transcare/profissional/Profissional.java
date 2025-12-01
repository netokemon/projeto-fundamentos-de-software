package com.usforus.transcare.profissional;

public class Profissional {

    private Long id;
    private String nome;
    private String especializacao;
    private String endereco;

    public Profissional(Long id, String nome, String especializacao, String endereco) {
        this.id = id;
        this.nome = nome;
        this.especializacao = especializacao;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public String getEndereco() {
        return endereco;
    }

}
