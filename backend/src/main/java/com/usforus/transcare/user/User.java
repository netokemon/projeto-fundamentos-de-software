package com.usforus.transcare.user;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    private String nomeSocial;
    private String pronomes;
    private LocalDate dataNascimento;
    private String identidadeGenero;
    private String etapaTransicao;
    private String regiao;

    public User() {
    }

    public User(String nomeCompleto, String email, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeSocial() { return nomeSocial; }
    public void setNomeSocial(String nomeSocial) { this.nomeSocial = nomeSocial; }

    public String getPronomes() { return pronomes; }
    public void setPronomes(String pronomes) { this.pronomes = pronomes; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getIdentidadeGenero() { return identidadeGenero; }
    public void setIdentidadeGenero(String identidadeGenero) { this.identidadeGenero = identidadeGenero; }

    public String getEtapaTransicao() { return etapaTransicao; }
    public void setEtapaTransicao(String etapaTransicao) { this.etapaTransicao = etapaTransicao; }

    public String getRegiao() { return regiao; }
    public void setRegiao(String regiao) { this.regiao = regiao; }
}
