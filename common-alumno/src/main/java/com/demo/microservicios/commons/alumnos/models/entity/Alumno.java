package com.demo.microservicios.commons.alumnos.models.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "alumnos")
@Data
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{NotEmpty.es}")
    private String nombre;

    @NotEmpty(message = "{NotEmpty.es}")
    private String apellido;

    @NotEmpty(message = "{NotEmpty.es}")
    @Email(message = "{Email.es}")
    private String email;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Lob
    @JsonIgnore
    private byte[] foto;

    @PrePersist
    public void prePeresit() {
        this.createAt = LocalDateTime.now();
    }

    public Integer getFotoHashCode(){
        return (this.foto != null)?this.foto.hashCode():null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;
        if (!(obj instanceof Alumno)) {
            return false;
        }
        Alumno a = (Alumno) obj;
        return this.id != null && this.id.equals(a.getId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

}
