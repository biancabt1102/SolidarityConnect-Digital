package br.com.solidarityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.solidarityconnect.models.Doacao;

public interface DoacaoRepository extends JpaRepository<Doacao, Long>{
    
}
