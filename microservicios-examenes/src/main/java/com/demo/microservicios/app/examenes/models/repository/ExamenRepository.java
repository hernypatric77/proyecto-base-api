package com.demo.microservicios.app.examenes.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.demo.microservicios.commons.examenes.models.entity.Examen;

public interface ExamenRepository extends JpaRepository<Examen, Long> {
    @Query("select e from Examen e where e.nombre like %?1%")
    public List<Examen> findByName(String term);

}
