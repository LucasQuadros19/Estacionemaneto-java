package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.Entity.Modelo;
import br.com.uniamerica.estacionamento.Entity.Movimentacao;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
