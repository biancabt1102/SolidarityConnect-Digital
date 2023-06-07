package br.com.solidarityconnect.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import br.com.solidarityconnect.models.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Page<Usuario> findByNomeUsuarioContaining(String busca, Pageable pageable);

    Optional<Usuario> findByEmailUsuario(String emailUsuario);

    @Query("SELECT u FROM Usuario u WHERE u.emailUsuario = :email")
    Usuario findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nomeUsuario) LIKE LOWER(concat('%', :busca, '%'))")
    List<Usuario> findByNomeUsuarioContaining(String busca);

    @Query("SELECT u FROM Usuario u WHERE u.cnpjUsuario = :cnpj")
    List<Usuario> findByCnpj(String cnpj);

    @Query("SELECT u FROM Usuario u ORDER BY u.id DESC")
    Optional<Usuario> findFirstByOrderByIdDesc();

    Usuario findTopByOrderByIdUsuarioDesc();

}
