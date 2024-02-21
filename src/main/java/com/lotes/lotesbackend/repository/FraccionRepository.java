package com.lotes.lotesbackend.repository;

import com.lotes.lotesbackend.constants.Estatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lotes.lotesbackend.entity.Fraccion;

import java.util.List;

@Repository
public interface FraccionRepository extends JpaRepository<Fraccion, Long> {

    public List<Fraccion> getFraccionsByProyectoId(Long proyectoId);

    List<Fraccion> findByProyectoIdAndColindanciaProyecto(Long proyectoId, boolean colindanciaProyecto);

    public List<Fraccion> findByProyectoId(Long proyectoId);

    public List<Fraccion> getFraccionsByEstatus(Estatus estatus);

}
