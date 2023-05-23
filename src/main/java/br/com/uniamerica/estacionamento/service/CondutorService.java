package br.com.uniamerica.estacionamento.service;
import br.com.uniamerica.estacionamento.Entity.Condutor;
import br.com.uniamerica.estacionamento.Entity.Veiculo;
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
        Assert.isTrue(condutor.getNome() != null , "Error digite um numero");
        Assert.isTrue(condutor.getTelefone() != null, "Error digite uma telefone");
        Assert.isTrue(this.condutorRepository.findTelefonesCadastro(condutor.getTelefone()).isEmpty(),"Telefone ja existe");
        String regexTelefone = "\\+\\d{3}\\(\\d{2}\\)\\d{5}-\\d{4}";
        Assert.isTrue(condutor.getTelefone().matches(regexTelefone), "Mascara de telefone invalida");
        Assert.isTrue(this.condutorRepository.findCpfCadastro(condutor.getCpf()).isEmpty(),"Error CPF ja existe");
        Assert.isTrue(condutor.getCpf() != null, "CPF, nao informado");
        String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";
        Assert.isTrue(condutor.getCpf().matches(regexCpf), "Error cpf com mascara errada");

        this.condutorRepository.save(condutor);
    }
    public List<Condutor> listaCompleta() {
        return this.condutorRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, Condutor atualizar) {
        final Condutor marcaBanco = this.condutorRepository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        // pq isso nao da ceto
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()),"nao foi possivel identificar o registro");
        this.condutorRepository.save(atualizar);
    }




}
