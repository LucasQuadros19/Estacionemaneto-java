package br.com.uniamerica.estacionamento.Entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Condutor extends AbstractEntity {

    private String nome;
    private  String cpf;
    private String telefone;
    private LocalTime tempoPago;
    private LocalTime tempoDesconto;

    public Condutor(LocalDateTime cadastro, LocalDateTime edicao, boolean ativo, String nome, String cpf, String telefone, LocalTime tempoPago, LocalTime tempoDesconto) {
        super(cadastro, edicao, ativo);
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.tempoPago = tempoPago;
        this.tempoDesconto = tempoDesconto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalTime getTempoPago() {
        return tempoPago;
    }

    public void setTempoPago(LocalTime tempoPago) {
        this.tempoPago = tempoPago;
    }

    public LocalTime getTempoDesconto() {
        return tempoDesconto;
    }

    public void setTempoDesconto(LocalTime tempoDesconto) {
        this.tempoDesconto = tempoDesconto;
    }
}
