package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.Entity.Condutor;
import br.com.uniamerica.estacionamento.Entity.Marca;
import br.com.uniamerica.estacionamento.Entity.Veiculo;
import br.com.uniamerica.estacionamento.Entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    @Modifying
    @Query(value = "UPDATE veiculos  SET ativo = false WHERE id = :id",nativeQuery = true)
    public void desativar(@Param("id") Long id);

    List<Veiculo> findByAtivo(boolean ativo);
}
