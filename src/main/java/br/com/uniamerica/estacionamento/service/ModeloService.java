package br.com.uniamerica.estacionamento.service;
import br.com.uniamerica.estacionamento.Entity.Modelo;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.List;
@Service
public class ModeloService {
    @Autowired
    private ModeloRepository modeloRepository;
    public List<Modelo> listartudo(){

        return modeloRepository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Modelo cadastrar(Modelo cadastrar) {
        if (cadastrar.getNome() == null || cadastrar.getNome().isEmpty()) {
            throw new IllegalArgumentException("Error: nome vazio");
        }
        int count = this.modeloRepository.countByNome(cadastrar.getNome());
        if (count > 0) {
            throw new RuntimeException("Erro: O modelo já existe");
        }
        return this.modeloRepository.save(cadastrar);
    }
    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, Modelo atualizar) {
        final Modelo marcaBanco = this.modeloRepository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");


        // pq isso nao da ceto
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()),"nao foi possivel identificar o registro");
        this.modeloRepository.save(atualizar);
    }






}
