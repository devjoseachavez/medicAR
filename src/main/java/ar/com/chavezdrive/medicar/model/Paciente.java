package ar.com.chavezdrive.medicar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "pacientes")
public class Paciente extends Usuario {
    private String grupoSanguineo;
    private String nroAfiliado;
    private String obraSocial;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private List<Turno> turnos;
}
