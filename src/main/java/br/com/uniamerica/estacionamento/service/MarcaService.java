package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.Entity.Marca;
import br.com.uniamerica.estacionamento.Entity.Modelo;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository  marcaRepository;

    public List<Marca> listartudo(){

        return marcaRepository.findAll();
    }
    @Transactional
    public Marca cadastrar(Marca marca) {
        if(marca.getNome().trim().isEmpty()){
            throw  new RuntimeException("Erro: marca nula!!");
        }else{
            return this.marcaRepository.save(marca);
        }
    }
    @Transactional
    public void atualizar(Long id, Marca marca) {
        if(id == marca.getId()) {
            this.marcaRepository.save(marca);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void desativar(Long id){
        var marca = this.marcaRepository.findById(id);
        if (id == marca.get().getId()) {
            this.marcaRepository.desativar(id);
        }
        else {
            throw new RuntimeException();
        }
    }


}
