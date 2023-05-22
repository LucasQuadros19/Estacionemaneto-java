package br.com.uniamerica.estacionamento.service;
import br.com.uniamerica.estacionamento.Entity.Marca;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
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
        Assert.isTrue(marca.getNome() != null,"Error nome vazio");
            return this.marcaRepository.save(marca);
    }
    @Transactional
    public void atualizar(Long id, Marca marca) {
        final Marca marcaBanco = this.marcaRepository.findById(marca.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null || !marcaBanco.getId().equals(marca.getId()),"nao foi possivel identificar o registro");
        this.marcaRepository.save(marca);
    }



}
