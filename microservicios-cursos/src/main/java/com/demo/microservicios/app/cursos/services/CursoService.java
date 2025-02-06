package com.demo.microservicios.app.cursos.services;

import com.demo.microservicios.app.commons.services.CommonService;
import com.demo.microservicios.app.cursos.models.entity.Curso;

public interface CursoService extends CommonService<Curso> {

    public Curso findCursoByAlumnoId(Long id);
}
