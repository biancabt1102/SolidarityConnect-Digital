package br.com.solidarityconnect.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.solidarityconnect.controllers.UsuarioController;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_SC_USUARIO")
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_usuario", columnDefinition = "NUMBER(4)")
    private Long idUsuario;

    @NotNull
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 50)
    @Column(name = "nm_usuario", columnDefinition = "VARCHAR2(50)")
    private String nomeUsuario;

    @NotNull
    @NotBlank(message="O email é obrigatório")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @Column(name = "ds_email", columnDefinition = "VARCHAR2(50)", unique = true)
    private String emailUsuario;

    @NotNull
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8)
    @Column(name = "ds_senha")
    private String senhaUsuario;

    @NotNull
    @NotBlank(message = "O cnpj é obrigatória")
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$")
    @Column(name = "ds_cnpj", columnDefinition = "CHAR(18)")
    private String cnpjUsuario;
    
    @NotNull
    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "^\\(?\\d{2}\\)?[\\s-]?\\d{4,5}-\\d{4}$")
    @Column(name = "nr_telefone", columnDefinition = "CHAR(15)")
    private String telefoneUsuario;

    public EntityModel<Usuario> toEntityModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(UsuarioController.class).show(idUsuario)).withSelfRel(),
            linkTo(methodOn(UsuarioController.class).delete(idUsuario)).withRel("delete"),
            linkTo(methodOn(UsuarioController.class).index(null, Pageable.unpaged())).withRel("all")
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
    }

    @Override
    public String getPassword() {
        return senhaUsuario;
    }

    @Override
    public String getUsername() {
        return emailUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

