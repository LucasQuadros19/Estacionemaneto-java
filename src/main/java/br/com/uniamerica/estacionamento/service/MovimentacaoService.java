package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.Entity.Configuracao;
import br.com.uniamerica.estacionamento.Entity.Modelo;
import br.com.uniamerica.estacionamento.Entity.Movimentacao;
import br.com.uniamerica.estacionamento.Entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Transactional
    public Movimentacao cadastrar(Movimentacao movimentacao) {
        return this.movimentacaoRepository.save(movimentacao);
    }


    public List<Movimentacao> listaCompleta() {
        return this.movimentacaoRepository.findAll();
    }

    public Movimentacao findById(Long id) {
        return this.movimentacaoRepository.findById(id).orElse(new Movimentacao());
    }
    @Transactional
    public void atualizar(Long id, Movimentacao movimentacao) {
        if(id == movimentacao.getId()) {
            this.movimentacaoRepository.save(movimentacao);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void desativar(Long id){
        var movimentacao = this.movimentacaoRepository.findById(id);
        if (id == movimentacao.get().getId()) {
            this.movimentacaoRepository.desativar(id);
        }
        else {
            throw new RuntimeException();
        }
    }

    public void tempo(Movimentacao movimentacao){

        if( movimentacao.getSaida()==null ){
            throw new RuntimeException("saida nao finalizada " + movimentacao.getSaida());
        }else{
            Configuracao configuracao = new Configuracao();
            Veiculo veiculo = new Veiculo();
            LocalDateTime dateTime1 = movimentacao.getEntrada();
            LocalDateTime dateTime2 = movimentacao.getSaida();
            long secondsBetween = ChronoUnit.SECONDS.between(dateTime1, dateTime2);
            Duration duration = Duration.ofSeconds(secondsBetween);
            long minutes = duration.toMinutes();
            BigDecimal valor = configuracao.getValorHora();
            BigDecimal multa = configuracao.getValorMinutoMulta();
            LocalTime inicio = configuracao.getInicioExpediente();
            LocalTime fim = configuracao.getFimExpediente();
            LocalDateTime cadastro = veiculo.getCadastro();
            LocalDateTime edicao = veiculo.getEdicao();
            LocalTime desconto = configuracao.getTempoParaDesconto();
            LocalTime paraDesconto = configuracao.getTempoParaDesconto();
            double total;



            if(cadastro.isAfter(ChronoLocalDateTime.from(inicio)) || edicao.isBefore(ChronoLocalDateTime.from(fim))) {
                total = minutes * valor.doubleValue();
                long horas = minutes / 60;
                long minutosRestantes = minutes % 60;
                System.out.println(horas + " horas e " + minutosRestantes + " minutos=R$" + total);

            } else if (edicao.isAfter(ChronoLocalDateTime.from(fim))) {


            }else if (edicao.isAfter(ChronoLocalDateTime.from(fim))){

            }else{
                total = minutes * valor.doubleValue();

            }










            System.out.printf("Duração: %d ",minutes);


        }

    }



}
