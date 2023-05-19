package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.Entity.Marca;
import br.com.uniamerica.estacionamento.Entity.Modelo;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModeloService {
    @Autowired
    private ModeloRepository modeloRepository;


    public List<Modelo> listartudo(){

        return modeloRepository.findAll();
    }
    @Transactional
    public Modelo cadastrar(Modelo cadastrar) {
        if(cadastrar.getNome().trim().isEmpty()){
            throw  new RuntimeException("Erro: marca nula!!");
        }else{
            return this.modeloRepository.save(cadastrar);
        }
    }
    @Transactional
    public void atualizar(Long id, Modelo atualizar) {
        if(id == atualizar.getId()) {
            this.modeloRepository.save(atualizar);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void desativar(Long id){
        var modelo = this.modeloRepository.findById(id);
        if (id == modelo.get().getId()) {
            this.modeloRepository.desativar(id);
        }
        else {
            throw new RuntimeException();
        }
    }




}
