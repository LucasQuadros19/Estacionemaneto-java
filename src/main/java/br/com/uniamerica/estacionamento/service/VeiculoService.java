package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.Entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;


    public List<Veiculo> listartudo(){

        return this.veiculoRepository.findAll();
    }
    @Transactional
    public Veiculo cadastrar(Veiculo cadastrar) {
        return this.veiculoRepository.save(cadastrar);
    }
    @Transactional
    public void atualizar(Long id, Veiculo atualizar) {
        if(id == atualizar.getId()) {
            this.veiculoRepository.save(atualizar);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void desativar(Long id){
        var Veiculo = this.veiculoRepository.findById(id);
        if (id == Veiculo.get().getId()) {
            this.veiculoRepository.desativar(id);
        }
        else {
            throw new RuntimeException();
        }
    }




}
