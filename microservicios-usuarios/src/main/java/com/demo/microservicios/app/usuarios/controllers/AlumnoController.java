package com.demo.microservicios.app.usuarios.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.demo.microservicios.app.commons.controllers.CommonController;
import com.demo.microservicios.app.usuarios.services.AlumnoService;
import com.demo.microservicios.commons.alumnos.models.entity.Alumno;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

@RestController
// @RequestMapping("/alumno/")
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

    @GetMapping("uploads/img/{id}")
    public ResponseEntity<?> verFoto(@PathVariable Long id){
        Optional<Alumno> o = service.findById(id);
        if (o.isEmpty() || o.get().getFoto() == null) {
            return ResponseEntity.notFound().build();
        }
        Resource imagen = new ByteArrayResource(o.get().getFoto());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imagen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        Optional<Alumno> o = service.findById(id);
        if (o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Alumno alumnoDb = o.get();
        alumnoDb.setNombre(alumno.getNombre());
        alumnoDb.setApellido(alumno.getApellido());
        alumnoDb.setEmail(alumno.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
    }

    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> getMethodName(@PathVariable String term) {
        return ResponseEntity.ok(service.findByNombreOrApellido(term));
    }

    @PostMapping("/crear-con-foto")
    public ResponseEntity<?> crearConFoto(Alumno alumno, BindingResult result,
                                          @RequestParam MultipartFile archivo) throws IOException {
        if(!archivo.isEmpty()){
            alumno.setFoto(archivo.getBytes());
        }
        return super.crear(alumno, result);
    }

    @PutMapping("/editar-con-foto/{id}")
    public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, @PathVariable Long id, BindingResult result,
                                           @RequestParam MultipartFile archivo) throws IOException {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        Optional<Alumno> o = service.findById(id);
        if (o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Alumno alumnoDb = o.get();
        alumnoDb.setNombre(alumno.getNombre());
        alumnoDb.setApellido(alumno.getApellido());
        alumnoDb.setEmail(alumno.getEmail());

        if(!archivo.isEmpty()){
            alumnoDb.setFoto(archivo.getBytes());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
    }
}
