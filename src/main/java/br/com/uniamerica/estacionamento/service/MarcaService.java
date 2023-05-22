package br.com.uniamerica.estacionamento.service;
import br.com.uniamerica.estacionamento.Entity.Marca;
import br.com.uniamerica.estacionamento.Entity.Modelo;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository  marcaRepository;
    public List<Marca> listartudo(){
        return marcaRepository.findAll();
    }
    @Transactional(rollbackFor = RuntimeException.class)
    public Marca cadastrar(Marca marca) {
        if (marca.getNome() == null || marca.getNome().isEmpty()) {
            throw new IllegalArgumentException("Error: nome vazio");
        }
        int count = this.marcaRepository.countByNome(marca.getNome());
        if (count > 0) {
            throw new RuntimeException("Error: marca já existe");
        }
        return this.marcaRepository.save(marca);
    }




    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, Marca marca) {
        final Marca marcaBanco = this.marcaRepository.findById(marca.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null && !marcaBanco.getId().equals(marca.getId()),"nao foi possivel identificar o registro");
        this.marcaRepository.save(marca);
    }


    @Transactional(rollbackFor = Exception.class)
    public void deletar (final Marca marca){
        final Marca marcaBanco = this.marcaRepository.findById(marca.getId()).orElse(null);

        List<Modelo> modeloLista = this.marcaRepository.findModelo(marcaBanco);

        if(modeloLista.isEmpty()){
            this.marcaRepository.delete(marcaBanco);
            System.out.println("ola1");
        }else{
            marcaBanco.setAtivo(false);
            System.out.println("ola2");
            this.marcaRepository.save(marcaBanco);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void editar(Long id, Marca marca) {
        final Optional<Marca> marcaOptional = this.marcaRepository.findById(marca.getId());
        if (marcaOptional.isPresent()) {
            Marca marcaBanco = marcaOptional.get();
            Assert.isTrue(marcaBanco.getId().equals(id), "Error: ID da URL diferente do body");
            marcaBanco.setNome(marca.getNome());
            // Atualize outros atributos da marca se necessário
            this.marcaRepository.save(marcaBanco);
        } else {
            throw new RuntimeException("Não foi possível identificar o registro");
        }
    }



}
