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


    @Transactional
    public Condutor cadastrar2(Condutor condutor) {
        if(condutor.getNome().trim().isBlank() || ValidaCpf.isCPF(condutor.getCpf()) == false) {
            throw new RuntimeException("Cpf invalido: " + condutor.getCpf());
        }else if(ValidarTelefone.telefoneValido(condutor.getTelefone()) == false){
            throw new RuntimeException("Telefone invalido: " + condutor.getTelefone());
        }
        if (condutor.getNome() == null || condutor.getNome().isEmpty()) {
            throw new RuntimeException("Insira um nome para o condutor");
        }
        if (condutor.getCpf() == null || condutor.getCpf().isEmpty()) {
            throw new RuntimeException("Insira um CPF para o condutor");
        }
        if (condutor.getTelefone() == null || condutor.getTelefone().isEmpty()) {
            throw new RuntimeException("Insira um telefone para o condutor");
        }
        if (condutor.getCpf().length() > 15) {
            throw new RuntimeException("O CPF do condutor excedeu o limite de 15 caracteres, confirme se o CPF esta correto");
        }
        if (condutor.getTelefone().length() > 17) {
            throw new RuntimeException("O numero de telefone do condutor excedeu o limite de caracteres, confirme se o seu numero esta correto");
        }
        if (condutor.getNome().length() > 100) {
            throw new RuntimeException("O nome do condutor excedeu o limite de caracteres, por favor altere seu nome");
        }
        else {
            return this.condutorRepository.save(condutor);
        }
    }
    @Transactional
    public Condutor cadastrar3(Condutor con) {
        if(con.getNome().trim().isEmpty()){
            throw  new RuntimeException("Erro: marca nula!!");
        }else{
            return this.condutorRepository.save(con);
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


}
