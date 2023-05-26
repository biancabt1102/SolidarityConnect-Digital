package br.com.solidarityconnect.models;


import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import br.com.solidarityconnect.controllers.EnderecoController;
import br.com.solidarityconnect.controllers.UsuarioController;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_SC_ENDERECO")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_endereco", columnDefinition = "NUMBER(4)")
    private Long idEndereco;

    @NotNull
    @NotBlank(message = "O logradouro é obrigatório")
    @Column(name = "nm_endereco", columnDefinition = "VARCHAR2(50)")
    private String logradouroEndereco;

    @NotNull
    @Min(1)
    @Column(name = "nr_endereco", columnDefinition = "NUMBER(4)")
    private int numeroEndereco;

    @NotNull
    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "^\\d{5}-\\d{3}$")
    @Column(name = "ds_cep", columnDefinition = "CHAR(9)")
    private String cepEndereco;

    @NotNull
    @NotBlank(message = "O bairro é obrigatório")
    @Column(name = "nm_bairro", columnDefinition = "VARCHAR2(50)")
    private String bairroEndereco;

    @NotNull
    @NotBlank(message = "O UF é obrigatório")
    @Pattern(regexp = "^[A-Z]{2}$")
    @Column(name = "ds_uf", columnDefinition = "CHAR(2)")
    private String ufEndereco;
    
    @Nullable
    @Size(min = 2)
    @Column(name = "ds_complemento", columnDefinition = "VARCHAR2(20)")
    private String complementoEndereco;
    
    @ManyToOne
    @JoinColumn(name = "cd_usuario", columnDefinition = "NUMBER(4)")
    private Usuario usuario;

    public EntityModel<Endereco> toEntityModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(EnderecoController.class).show(idEndereco)).withSelfRel(),
            linkTo(methodOn(EnderecoController.class).delete(idEndereco)).withRel("delete"),
            linkTo(methodOn(EnderecoController.class).index(null, Pageable.unpaged())).withRel("all"),

            linkTo(methodOn(UsuarioController.class).show(this.getUsuario().getIdUsuario())).withRel("usuario")
        );
    }
}