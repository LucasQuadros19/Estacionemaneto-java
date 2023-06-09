package br.com.uniamerica.estacionamento.Entity;


import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "movimentacaos",schema = "public")
@Audited
@AuditTable(value = "MovimentacaoAudit", schema = "audit")
public class Movimentacao extends AbstractEntity{


    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="Movimentacao_veiculo",nullable=false)
    private Veiculo veiculo;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="Movimentacao_condutor",nullable=false)
    private Condutor condutor;
    @Getter
    @Setter
    @Column(name="entrada",nullable=false)
    private LocalDateTime entrada;
    @Getter
    @Setter
    @Column(name="saida")
    private LocalDateTime saida;
    @Getter
    @Setter
    @Column(name="horas")
    private Integer horas;
    @Getter
    @Setter
    @Column(name = "minutos")
    private Integer minutos;
    @Getter
    @Setter
    @Column(name="tempo_desconto")
    private Integer tempoDesconto;
    @Getter
    @Setter
    @Column(name="tempo_multa")
    private LocalTime tempoMulta;
    @Getter
    @Setter
    @Column(name="valor_desconto")
    private BigDecimal valorDesconto;
    @Getter
    @Setter
    @Column(name="valor_multa")
    private BigDecimal valorMulta;
    @Getter
    @Setter
    @Column(name="valor_total")
    private BigDecimal valorTotal;
    @Getter
    @Setter
    @Column(name="valor_hora")
    private BigDecimal valorHora;
    @Getter
    @Setter
    @Column(name="valor_hora_multa")
    private BigDecimal valorHoraMulta;

    @PrePersist
    private void prePersiste(){
        setCadastro(LocalDateTime.now());
        this.setEntrada(LocalDateTime.now());
        this.setTempoDesconto(0);
    }

}
