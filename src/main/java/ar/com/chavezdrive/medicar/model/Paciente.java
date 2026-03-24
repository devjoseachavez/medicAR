package ar.com.chavezdrive.medicar.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "pacientes")
public class Paciente extends Usuario {
    
    private String obraSocial;
    private String nroAfiliado;
    private String grupoSanguineo;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private List<Turno> turnos;
}