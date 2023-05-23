package br.com.uniamerica.estacionamento.service;
import br.com.uniamerica.estacionamento.Entity.Marca;
import br.com.uniamerica.estacionamento.Entity.Modelo;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.List;
@Service
public class MarcaService {
    @Autowired
    private MarcaRepository  marcaRepository;
    @Autowired
    ModeloRepository modeloRepository;
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
            throw new RuntimeException("Erro: A marca já existe");
        }
        return this.marcaRepository.save(marca);
    }
    @Transactional(rollbackFor = Exception.class)
    public void   atualizar(Long id, Marca marca) {
        final Marca marcaBanco = this.marcaRepository.findById(marca.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");


        // pq isso nao da ceto
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(marca.getId()),"nao foi possivel identificar o registro");
        this.marcaRepository.save(marca);
    }




    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Marca marca) {
        final Marca marcaBanco = this.marcaRepository.findById(marca.getId()).orElse(null);

        List<Modelo> modeloLista = this.marcaRepository.findModelo(marcaBanco);

        if (modeloLista.isEmpty()) {
            this.marcaRepository.delete(marcaBanco);
            System.out.println("ola1");
        } else {
            for (Modelo modelo : modeloLista) {
                modelo.setMarca(null); // Remover a referência da marca no modelo
                this.modeloRepository.delete(modelo);
                System.out.println("Modelo apagado: " + modelo.getId());
            }
            this.marcaRepository.delete(marcaBanco);
            System.out.println("ola2");
        }
    }
















}
