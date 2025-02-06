package com.demo.microservicios.app.examenes.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservicios.app.commons.controllers.CommonController;
import com.demo.microservicios.app.examenes.services.ExamenService;
import com.demo.microservicios.commons.examenes.models.entity.Examen;
import com.demo.microservicios.commons.examenes.models.entity.Pregunta;

import jakarta.validation.Valid;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> iditar(@Valid @PathVariable Long id, @RequestBody Examen examen, BindingResult result) {

        if (result.hasErrors()) {
            return this.validar(result);
        }

        Optional<Examen> o = service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Examen examenDb = o.get();
        examenDb.setNombre(examen.getNombre());

        List<Pregunta> preguntasParaEliminar = examenDb.getPreguntas()
                .stream()
                .filter(pdb -> examen.getPreguntas().contains(pdb))
                .collect(Collectors.toList());
        preguntasParaEliminar.forEach(examenDb::removePregunta);

        examenDb.setPreguntas(examen.getPreguntas());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
    }

    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> filtrar(@PathVariable String term) {
        return ResponseEntity.ok(service.findByName(term));
    }

    @GetMapping("/asignaturas")
    public ResponseEntity<?> listarAsignaturas() {
        return ResponseEntity.ok(service.findAllAsignaturas());
    }

}
