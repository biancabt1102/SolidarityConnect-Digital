package br.com.solidarityconnect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.solidarityconnect.models.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Page<Usuario> findByNomeContaining(String busca, Pageable pageable);

}
