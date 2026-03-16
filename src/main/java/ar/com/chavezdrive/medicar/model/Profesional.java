package ar.com.chavezdrive.medicar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "profesionales")
public class Profesional extends Usuario {
    private String matriculaProvincial;
    private String matriculaNacional;

    @ManyToMany
    @JoinTable(
        name = "profesional_especialidad",
        joinColumns = @JoinColumn(name = "profesional_id"),
        inverseJoinColumns = @JoinColumn(name = "especialidad_id")
    )
    private Set<Especialidad> especialidades;

    @OneToMany(mappedBy = "profesional")
    @JsonIgnore
    private List<Turno> turnos;
}
