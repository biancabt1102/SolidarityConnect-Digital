package br.com.solidarityconnect.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.solidarityconnect.models.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Page<Usuario> findByNomeUsuarioContaining(String busca, Pageable pageable);

    Optional<Usuario> findByEmailUsuario(String emailUsuario);


}
