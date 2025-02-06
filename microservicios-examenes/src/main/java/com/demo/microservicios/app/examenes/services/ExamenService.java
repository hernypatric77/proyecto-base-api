package com.demo.microservicios.app.examenes.services;

import java.util.List;

import com.demo.microservicios.app.commons.services.CommonService;
import com.demo.microservicios.commons.examenes.models.entity.Asignatura;
import com.demo.microservicios.commons.examenes.models.entity.Examen;

public interface ExamenService extends CommonService<Examen> {
    public List<Examen> findByName(String term);

    public Iterable<Asignatura> findAllAsignaturas();
}
