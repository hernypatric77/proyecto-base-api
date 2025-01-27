package com.demo.microservicios.app.usuarios.services;

import java.util.List;

import com.demo.microservicios.app.commons.services.CommonService;
import com.demo.microservicios.commons.alumnos.models.entity.Alumno;

public interface AlumnoService extends CommonService<Alumno> {

    public List<Alumno> findByNombreOrApellido(String term);

}
