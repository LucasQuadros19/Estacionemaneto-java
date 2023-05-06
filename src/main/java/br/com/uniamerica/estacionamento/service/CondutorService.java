package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.Components.ValidaCpf;
import br.com.uniamerica.estacionamento.Components.ValidarTelefone;
import br.com.uniamerica.estacionamento.Entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;

    @Transactional
    public Condutor cadastrar(Condutor condutor) {
        if(condutor.getNome().trim().isBlank() || ValidaCpf.isCPF(condutor.getCpf()) == false) {
            throw new RuntimeException("Cpf invalido: " + condutor.getCpf());
        }else if(ValidarTelefone.telefoneValido(condutor.getTelefone()) == false){
            throw new RuntimeException("Telefone invalido: " + condutor.getTelefone());
        }
        else {
            return this.condutorRepository.save(condutor);
        }
    }


    public List<Condutor> listaCompleta() {
        return this.condutorRepository.findAll();
    }

    public Condutor findById(Long id) {

        return this.condutorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id não encontrado: "+id));
    }
    @Transactional
    public void atualizar(Long id, Condutor condutor) {
        if(id == condutor.getId()) {
            this.condutorRepository.save(condutor);
        }else{
            throw new EntityNotFoundException("id não encontrado: "+id);
        }
    }

    @Transactional
    public void desativar(Long id){
        var condutor = this.condutorRepository.findById(id);
        if (id == condutor.get().getId()) {
            this.condutorRepository.desativar(id);
        }
        else {
            throw new RuntimeException();
        }
    }

    public List<Condutor> listaCondutoresAtivos(){
        return this.condutorRepository.CondutoresAtivos();
    }
}
