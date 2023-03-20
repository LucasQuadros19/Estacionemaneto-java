package br.com.uniamerica.estacionamento.Entity;

import java.time.LocalDateTime;

public class Veiculo extends AbstractEntity {
    private String placa;
    private Modelo modelo;
    private int ano;

    public Veiculo(LocalDateTime cadastro, LocalDateTime edicao, boolean ativo, String placa, Modelo modelo, int ano) {
        super(cadastro, edicao, ativo);
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
