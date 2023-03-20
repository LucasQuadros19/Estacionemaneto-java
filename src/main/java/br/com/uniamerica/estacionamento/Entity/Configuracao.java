package br.com.uniamerica.estacionamento.Entity;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Configuracao {
    private BigDecimal valorHora;
    private BigDecimal valorMinutoMulta;
    private LocalTime inicioExpediente;
    private LocalTime fimExpediente;
    private  LocalTime tempoParaDesconto;
    private  LocalTime tempoDeDEsconto;
    private  boolean gerarDesconto;
    private int vagasMoto;
    private int vagasCarro;
    private int VagasVam;

    public Configuracao(BigDecimal valorHora, BigDecimal valorMinutoMulta, LocalTime inicioExpediente, LocalTime fimExpediente, LocalTime tempoParaDesconto, LocalTime tempoDeDEsconto, boolean gerarDesconto, int vagasMoto, int vagasCarro, int vagasVam) {
        this.valorHora = valorHora;
        this.valorMinutoMulta = valorMinutoMulta;
        this.inicioExpediente = inicioExpediente;
        this.fimExpediente = fimExpediente;
        this.tempoParaDesconto = tempoParaDesconto;
        this.tempoDeDEsconto = tempoDeDEsconto;
        this.gerarDesconto = gerarDesconto;
        this.vagasMoto = vagasMoto;
        this.vagasCarro = vagasCarro;
        VagasVam = vagasVam;
    }

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }

    public BigDecimal getValorMinutoMulta() {
        return valorMinutoMulta;
    }

    public void setValorMinutoMulta(BigDecimal valorMinutoMulta) {
        this.valorMinutoMulta = valorMinutoMulta;
    }

    public LocalTime getInicioExpediente() {
        return inicioExpediente;
    }

    public void setInicioExpediente(LocalTime inicioExpediente) {
        this.inicioExpediente = inicioExpediente;
    }

    public LocalTime getFimExpediente() {
        return fimExpediente;
    }

    public void setFimExpediente(LocalTime fimExpediente) {
        this.fimExpediente = fimExpediente;
    }

    public LocalTime getTempoParaDesconto() {
        return tempoParaDesconto;
    }

    public void setTempoParaDesconto(LocalTime tempoParaDesconto) {
        this.tempoParaDesconto = tempoParaDesconto;
    }

    public LocalTime getTempoDeDEsconto() {
        return tempoDeDEsconto;
    }

    public void setTempoDeDEsconto(LocalTime tempoDeDEsconto) {
        this.tempoDeDEsconto = tempoDeDEsconto;
    }

    public boolean isGerarDesconto() {
        return gerarDesconto;
    }

    public void setGerarDesconto(boolean gerarDesconto) {
        this.gerarDesconto = gerarDesconto;
    }

    public int getVagasMoto() {
        return vagasMoto;
    }

    public void setVagasMoto(int vagasMoto) {
        this.vagasMoto = vagasMoto;
    }

    public int getVagasCarro() {
        return vagasCarro;
    }

    public void setVagasCarro(int vagasCarro) {
        this.vagasCarro = vagasCarro;
    }

    public int getVagasVam() {
        return VagasVam;
    }

    public void setVagasVam(int vagasVam) {
        VagasVam = vagasVam;
    }
}
