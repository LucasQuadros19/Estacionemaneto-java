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
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    List<Marca> findByAtivo(boolean ativo);
    List<Marca> findByMarca(String marca);
    @Modifying
    @Query("UPDATE Marca marca SET marca.ativo = false WHERE marca.id = :idMarca")
    public void desativar(@Param("idMarca") Long id);
}
