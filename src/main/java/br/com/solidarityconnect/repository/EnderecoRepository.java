package br.com.solidarityconnect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.solidarityconnect.models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
    Page<Endereco> findByLogradouroEnderecoContaining(String busca, Pageable pageable);
}
