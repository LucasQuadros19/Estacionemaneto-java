package br.com.uniamerica.estacionamento.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;
import java.time.LocalTime;
@Entity
@Table(name = "Configuracaos",schema = "public")
@Audited
@AuditTable(value = "ConfiguracaoAudit", schema = "audit")
public class Configuracao extends AbstractEntity {

    @Getter
    @Setter
    @Column(name="valor_hora",nullable=false)
    private BigDecimal valorHora;
    @Getter @Setter
    @Column(name="valor_minuto",nullable=false)
    private BigDecimal valorMinutoMulta;
    @Getter @Setter
    @Column(name="inicio_expediente",nullable=false)
    private LocalTime inicioExpediente;
    @Getter @Setter
    @Column(name="fim_expediente",nullable=false)
    private LocalTime fimExpediente;
    @Getter @Setter
    @Column(name="tempo_para_desconto",nullable=false)
    private  LocalTime tempoParaDesconto;
    @Getter @Setter
    @Column(name="tempo_de_desconto",nullable=false)
    private  LocalTime tempoDeDEsconto;
    @Getter @Setter
    @Column(name="gerar_desconto",nullable=false)
    private  boolean gerarDesconto;
    @Getter @Setter
    @Column(name="vagas_moto",nullable=false)
    private int vagasMoto;
    @Getter @Setter
    @Column(name="vagas_carro",nullable=false)
    private int vagasCarro;
    @Getter @Setter
    @Column(name="Vagas_vam",nullable=false)
    private int VagasVam;

}
