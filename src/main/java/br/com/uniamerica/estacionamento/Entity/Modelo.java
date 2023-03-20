package br.com.uniamerica.estacionamento.Entity;

import java.time.LocalDateTime;

public class Modelo extends AbstractEntity {
    private String Nome;
    private Marca marca;

    public Modelo(LocalDateTime cadastro, LocalDateTime edicao, boolean ativo, String nome, Marca marca) {
        super(cadastro, edicao, ativo);
        this.Nome = nome;
        this.marca = marca;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
}
