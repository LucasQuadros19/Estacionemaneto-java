package br.com.uniamerica.estacionamento;

import br.com.uniamerica.estacionamento.Entity.Condutor;
import br.com.uniamerica.estacionamento.Entity.Veiculo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Relatorio {

    @Getter @Setter
    private LocalDateTime entrada;

    @Getter @Setter
    private LocalDateTime saida;

    @Getter @Setter
    private Condutor condutor;

    @Getter @Setter
    private Veiculo veiculo;

    @Getter @Setter
    private Integer horas;

    @Getter @Setter
    private BigDecimal horasDesconto;

    @Getter @Setter
    private BigDecimal valor;

    @Getter @Setter
    private BigDecimal desconto;

    public Relatorio(LocalDateTime entrada, LocalDateTime saida, Condutor condutor, Veiculo veiculo, Integer horas, BigDecimal horasDesconto, BigDecimal valor, BigDecimal desconto) {
        this.entrada = entrada;
        this.saida = saida;
        this.condutor = condutor;
        this.veiculo = veiculo;
        this.horas = horas;
        this.horasDesconto = horasDesconto;
        this.valor = valor;
        this.desconto = desconto;
    }
}
