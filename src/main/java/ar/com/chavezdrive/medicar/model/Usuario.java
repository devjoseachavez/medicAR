
package ar.com.chavezdrive.medicar.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios")
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String dni;  
    private String apellido;
    private String nombre;
    private String sexo;
    private String direccion;
    private String telefono;
    
    @Column(unique = true)
    private String email;
    
    private String foto; // Ruta o URL de la imagen
    
    @Column(unique = true, nullable = false)
    private String usuario;
    
    private String password;
}