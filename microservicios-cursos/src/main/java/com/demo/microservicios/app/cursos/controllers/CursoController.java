package com.demo.microservicios.app.cursos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservicios.app.commons.controllers.CommonController;
import com.demo.microservicios.app.cursos.models.entity.Curso;
import com.demo.microservicios.app.cursos.services.CursoService;
import com.demo.microservicios.commons.alumnos.models.entity.Alumno;
import com.demo.microservicios.commons.examenes.models.entity.Examen;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id) {
        Optional<Curso> o = service.findById(id);
        if (o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = o.get();
        cursoDb.setNombre(curso.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDb));
    }

    @PutMapping("/{id}/asignar-alumnos")
    public ResponseEntity<?> asignarAlumno(@PathVariable Long id, @RequestBody List<Alumno> alumnos) {
        Optional<Curso> o = service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = o.get();
        alumnos.forEach(a -> {
            cursoDb.addAlumnos(a);
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDb));
    }

    @PutMapping("/{id}/elimnar-alumno")
    public ResponseEntity<?> elimnarAlumno(@PathVariable Long id, @RequestBody Alumno alumno) {
        Optional<Curso> o = service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = o.get();

        cursoDb.removeAlumnos(alumno);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDb));
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id) {
        Curso curso = service.findCursoByAlumnoId(id);
        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{id}/asignar-examenes")
    public ResponseEntity<?> asignarExamenes(@PathVariable Long id, @RequestBody List<Examen> examenes) {
        Optional<Curso> o = service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = o.get();
        examenes.forEach(e -> {
            cursoDb.addExamen(e);
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDb));
    }

    @PutMapping("/{id}/elimnar-examen")
    public ResponseEntity<?> elimnarExamen(@PathVariable Long id, @RequestBody Examen examen) {
        Optional<Curso> o = service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = o.get();

        cursoDb.removeExamen(examen);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDb));
    }

}
