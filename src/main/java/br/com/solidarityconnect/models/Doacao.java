package br.com.solidarityconnect.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "T_SC_DOACAO")
public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_doacao", columnDefinition = "NUMBER(4)")
    private Long idDoacao;
    
    @NotNull
    @Column(name = "dt_doacao")
    private LocalDateTime dataDoacao;

    @PrePersist
    public void prePersist() {
        dataDoacao = LocalDateTime.now();
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cd_usuario", columnDefinition = "NUMBER(4)")
    private Usuario usuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cd_alimento", columnDefinition = "NUMBER(4)")
    private Alimento alimento;
}
