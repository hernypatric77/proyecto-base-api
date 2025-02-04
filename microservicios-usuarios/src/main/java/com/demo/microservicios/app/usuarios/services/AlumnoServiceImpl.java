package com.demo.microservicios.app.usuarios.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.microservicios.app.commons.services.CommonServiceImpl;
import com.demo.microservicios.app.usuarios.models.repository.AlumnoRepository;
import com.demo.microservicios.commons.alumnos.models.entity.Alumno;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findByNombreOrApellido(String term) {
        return repository.findByNombreOrApellido(term);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Alumno> findAll() {
        return repository.findAllByOrderByIdAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Alumno> findAll(Pageable pageable) {
        return repository.findAllByOrderByIdAsc(pageable);
    }

}
