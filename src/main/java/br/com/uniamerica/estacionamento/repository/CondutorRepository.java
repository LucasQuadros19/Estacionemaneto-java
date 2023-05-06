package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.Entity.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CondutorRepository extends JpaRepository<Condutor, Long> {
    @Modifying
    @Query("UPDATE Condutores condutor SET condutor.ativo = false WHERE condutor.id = :id")
    public void desativar(@Param("id") Long id);

    @Query("SELECT Condutores FROM Condutores condutor WHERE condutor.ativo = true")
    public List<Condutor> CondutoresAtivos();
}
