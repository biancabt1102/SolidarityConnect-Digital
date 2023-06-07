package br.com.solidarityconnect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import br.com.solidarityconnect.models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
    Page<Endereco> findByLogradouroEnderecoContaining(String busca, Pageable pageable);

    @Query("SELECT e FROM Endereco e WHERE LOWER(e.logradouroEndereco) LIKE LOWER(concat('%', :busca, '%'))")
    List<Endereco> findByLogradouroEnderecoContaining(String busca);

    @Query("SELECT e FROM Endereco e WHERE e.bairroEndereco = :bairro")
    List<Endereco> findByBairroEndereco(String bairro);

    @Query("SELECT e FROM Endereco e WHERE e.usuario.nomeUsuario = :nomeUsuario")
    List<Endereco> findByNomeUsuario(String nomeUsuario);
}
