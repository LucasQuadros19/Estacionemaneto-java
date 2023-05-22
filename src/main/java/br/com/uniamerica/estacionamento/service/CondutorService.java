package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.Components.ValidaCpf;
import br.com.uniamerica.estacionamento.Components.ValidarTelefone;
import br.com.uniamerica.estacionamento.Entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;
    @Transactional(rollbackFor =  Exception.class)
    public void cadastrar(final Condutor condutor){

        //Verifica o NOME
        Assert.isTrue(condutor.getNome() != null , "Error digite um numero");

        //Verifica o TELEFONE
        Assert.isTrue(condutor.getTelefone() != null, "Error digite uma telefone");
        Assert.isTrue(this.condutorRepository.findTelefonesCadastro(condutor.getTelefone()).isEmpty(),"Telefone ja existe");
        String regexTelefone = "\\+\\d{2}\\(\\d{3}\\)\\d{5}-\\d{4}";
        Assert.isTrue(!condutor.getTelefone().matches(regexTelefone), "Mascara de telefone invalida");

        //Verificar o CPF
        Assert.isTrue(this.condutorRepository.findCpfCadastro(condutor.getCpf()).isEmpty(),"Error CPF ja existe");
        Assert.isTrue(condutor.getCpf() != null, "CPF, nao informado");
        String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";
        Assert.isTrue(condutor.getCpf().matches(regexCpf), "Error cpf com mascara errada");

        this.condutorRepository.save(condutor);
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


}
