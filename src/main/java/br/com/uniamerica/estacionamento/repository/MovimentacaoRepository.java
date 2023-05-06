package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.Entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    @Modifying
    @Query("UPDATE Movimentacoes movimentacao SET movimentacao.ativo = false WHERE movimentacao.id = :idMovimentacao")
    public void desativar(@Param("idMovimentacao") Long id);

    @Query("SELECT movimentacao FROM Movimentacoes movimentacao WHERE movimentacao.ativo = true")
    public List<Movimentacao> MovimentacoesAtivas();
}
