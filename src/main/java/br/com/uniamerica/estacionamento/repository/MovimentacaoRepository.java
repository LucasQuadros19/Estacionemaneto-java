package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.Entity.Marca;
import br.com.uniamerica.estacionamento.Entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByAtivo(boolean ativo);
    @Modifying
    @Query(value = "UPDATE movimentacaos  SET ativo = false WHERE id = :id",nativeQuery = true)
    public void desativar(@Param("id") Long id);


}
