package com.usforus.transcare.profissional;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ProfissionalService {

    private final List<Profissional> profissionais = new ArrayList<>();

    public ProfissionalService() {

        profissionais.add(new Profissional(1L, "Dra. Fernanda Costa", "ENDOCRINOLOGIA", "Av. República do Líbano, 251, Torre Trade - Pina, Recife"));
        profissionais.add(new Profissional(5L, "Dr. Tiago Albuquerque", "ENDOCRINOLOGIA", "Av. Conde da Boa Vista, 800, Boa Vista - Recife"));
        profissionais.add(new Profissional(9L, "Dr. Paulo Mendes", "ENDOCRINOLOGIA", "Av. 17 de Agosto, 800, Casa Forte - Recife"));
        profissionais.add(new Profissional(10L, "Dra. Mariana Lins", "ENDOCRINOLOGIA", "Rua Padre Carapuceiro, 777, Shopping Recife - Boa Viagem, Recife"));
        profissionais.add(new Profissional(11L, "Dr. Felipe Barros", "ENDOCRINOLOGIA", "Av. Getúlio Vargas, 1400, Bairro Novo - Olinda"));

        profissionais.add(new Profissional(2L, "Psi. Lucas Oliveira", "PSICOLOGIA", "Rua do Espinheiro, 390, Empresarial Espinheiro - Recife"));
        profissionais.add(new Profissional(12L, "Psi. Beatriz Vasconcelos", "PSICOLOGIA", "Rua das Graças, 120, Graças - Recife"));
        profissionais.add(new Profissional(13L, "Psi. Rafael Torres", "PSICOLOGIA", "Av. Gov. Carlos de Lima Cavalcanti, 1500, Casa Caiada - Olinda"));
        profissionais.add(new Profissional(14L, "Psi. Vanessa Ribeiro", "PSICOLOGIA", "Av. Ayrton Senna da Silva, 300, Piedade - Jaboatão dos Guararapes"));

        profissionais.add(new Profissional(3L, "Dr. Roberto Menezes", "PSIQUIATRIA", "Rua Francisco Alves, 325, Ilha do Leite - Recife"));
        profissionais.add(new Profissional(15L, "Dra. Renata Farias", "PSIQUIATRIA", "Rua Benfica, 500, Madalena - Recife"));
        profissionais.add(new Profissional(16L, "Dr. Humberto Vieira", "PSIQUIATRIA", "Av. Agamenon Magalhães, 4775, Ilha do Leite - Recife"));

        profissionais.add(new Profissional(4L, "Dra. Juliana Santos", "GINECOLOGIA", "Av. Conselheiro Aguiar, 4635, Boa Viagem - Recife"));
        profissionais.add(new Profissional(17L, "Dra. Cláudia Siqueira", "GINECOLOGIA", "Av. Bernardo Vieira de Melo, 200, Piedade - Jaboatão dos Guararapes"));
        profissionais.add(new Profissional(18L, "Dra. Patrícia Nogueira", "GINECOLOGIA", "Rua Real da Torre, 1020, Torre - Recife"));
        profissionais.add(new Profissional(19L, "Dr. Gustavo Lima", "GINECOLOGIA", "Rua da Hora, 450, Espinheiro - Recife"));


        profissionais.add(new Profissional(6L, "Dr. Marcos Paulo", "UROLOGIA", "Praça do Derby, 149, Derby - Recife"));
        profissionais.add(new Profissional(20L, "Dr. Fábio Azevedo", "UROLOGIA", "Av. Mal. Mascarenhas de Morais, 2000, Imbiribeira - Recife"));
        profissionais.add(new Profissional(21L, "Dr. Ricardo Campos", "UROLOGIA", "Rua da Aurora, 300, Santo Amaro - Recife"));

        profissionais.add(new Profissional(7L, "Dra. Camila Rocha", "DERMATOLOGIA", "Rua do Futuro, 560, Jaqueira - Recife"));
        profissionais.add(new Profissional(22L, "Dr. Gabriel Souza", "DERMATOLOGIA", "Estrada do Encanamento, 800, Parnamirim - Recife"));
        profissionais.add(new Profissional(23L, "Dra. Larissa Brito", "DERMATOLOGIA", "Av. Herculano Bandeira, 450, Pina - Recife"));

        profissionais.add(new Profissional(8L, "Dr. André Gomes", "CLINICA_GERAL", "Rua Paissandu, 700, Paissandu - Recife"));
        profissionais.add(new Profissional(24L, "Dr. Fernando Melo", "CLINICA_GERAL", "Av. Caxangá, 2000, Cordeiro - Recife"));
        profissionais.add(new Profissional(25L, "Dra. Isabela Moura", "CLINICA_GERAL", "Rua do Hospício, 600, Boa Vista - Recife"));
        profissionais.add(new Profissional(26L, "Dr. Marcelo Correia", "CLINICA_GERAL", "Estrada dos Remédios, 800, Afogados - Recife"));


    }

    public List<Profissional> buscar(String especializacao) {
        return profissionais.stream()
                .filter(p -> especializacao == null || p.getEspecializacao().equalsIgnoreCase(especializacao))
                .collect(Collectors.toList());
    }

    public List<Profissional> listarTodos() {
        return profissionais;
    }
}
