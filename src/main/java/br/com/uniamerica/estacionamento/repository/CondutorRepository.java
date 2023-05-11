package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.Entity.Condutor;
import br.com.uniamerica.estacionamento.Entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CondutorRepository extends JpaRepository<Condutor, Long> {
    @Modifying
    @Query(value="UPDATE condutores SET ativo = false WHERE id = :id", nativeQuery = true)
    public void desativar(@Param("id") Long id);

    List<Condutor> findByAtivo(boolean ativo);
}
