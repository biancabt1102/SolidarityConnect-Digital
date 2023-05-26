package br.com.solidarityconnect.models;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.solidarityconnect.controllers.AlimentoController;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_SC_ALIMENTOS")
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_alimento")
    private Long idAlimento;

    @NotNull
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 30)
    @Column(name = "nm_alimento")
    private String nomeAlimento;

    @Column(name = "dt_validade")
    private LocalDate validadeAlimento;

    @NotNull
    @Min(1)
    @Column(name = "qtd_disponivel")
    private int quantidadeAlimento;

    @NotNull
    @NotBlank(message = "O tipo é obrigatória")
    @Column(name = "tp_alimento")
    private String tipoAlimento;

    public EntityModel<Alimento> toEntityModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(AlimentoController.class).show(idAlimento)).withSelfRel(),
            linkTo(methodOn(AlimentoController.class).delete(idAlimento)).withRel("delete"),
            linkTo(methodOn(AlimentoController.class).index(null, Pageable.unpaged())).withRel("all")
        );
    }
}
