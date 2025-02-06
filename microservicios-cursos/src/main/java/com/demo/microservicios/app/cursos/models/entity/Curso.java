package com.demo.microservicios.app.cursos.models.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.demo.microservicios.commons.alumnos.models.entity.Alumno;
import com.demo.microservicios.commons.examenes.models.entity.Examen;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "cursos")
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Examen> examenes;

    public Curso() {
        this.alumnos = new ArrayList<>();
        this.examenes = new ArrayList<>();
    }

    @OneToMany(fetch = FetchType.LAZY)
    private List<Alumno> alumnos;

    @PrePersist
    public void prePeresit() {
        this.createAt = LocalDateTime.now();
    }

    public void addAlumnos(Alumno alumno) {
        this.alumnos.add(alumno);
    }

    public void removeAlumnos(Alumno alumno) {
        this.alumnos.remove(alumno);
    }

    public void addExamen(Examen examen) {
        this.examenes.add(examen);
    }

    public void removeExamen(Examen examen) {
        this.examenes.remove(examen);
    }

}
