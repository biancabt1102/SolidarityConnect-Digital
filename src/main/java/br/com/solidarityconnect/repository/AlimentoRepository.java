package br.com.solidarityconnect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.solidarityconnect.models.Alimento;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {
    
    Page<Alimento> findByNomeAlimentoContaining(String busca, Pageable pageable);

    @Query("SELECT a FROM Alimento a WHERE a.tipoAlimento = :tipo")
    List<Alimento> findByTipoAlimento(String tipo);

    @Query("SELECT a FROM Alimento a WHERE LOWER(a.nomeAlimento) LIKE LOWER(concat('%', :busca, '%'))")
    List<Alimento> findByNomeAlimentoContaining(String busca);

    @Query("SELECT a FROM Alimento a WHERE a.quantidadeAlimento > :quantidade")
    List<Alimento> findByQuantidadeAlimentoGreaterThan(int quantidade);


}
