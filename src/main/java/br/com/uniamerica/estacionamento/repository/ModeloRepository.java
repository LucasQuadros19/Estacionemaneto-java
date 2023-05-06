package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.Entity.Marca;
import br.com.uniamerica.estacionamento.Entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    List<Modelo> findByAtivo(boolean ativo);
    List<Marca> findByMarca(String marca);
    @Modifying
    @Query("UPDATE Marca modelos SET modelos.ativo = false WHERE marca.id = :id")
    public void desativar(@Param("id") Long id);

}
