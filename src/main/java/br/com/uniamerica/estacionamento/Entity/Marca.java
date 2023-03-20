package br.com.uniamerica.estacionamento.Entity;

import java.time.LocalDateTime;

public class Marca extends AbstractEntity {
    private String marca;

    public Marca(LocalDateTime cadastro, LocalDateTime edicao, boolean ativo, String marca) {
        super(cadastro, edicao, ativo);
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
