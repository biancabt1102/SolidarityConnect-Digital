package br.com.solidarityconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.solidarityconnect.models.Alimento;
import br.com.solidarityconnect.models.Doacao;
import br.com.solidarityconnect.models.Usuario;

public interface DoacaoRepository extends JpaRepository<Doacao, Long>{
    
    
    @Query("SELECT d FROM Doacao d WHERE d.usuario.idUsuario = :idUsuario")
    List<Doacao> findByIdUsuario(Long idUsuario);

    List<Doacao> findByAlimento(Alimento alimento);

    List<Doacao> findByUsuario(Usuario usuario);
}
