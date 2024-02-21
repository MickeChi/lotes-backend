package com.lotes.lotesbackend.repository;

import com.lotes.lotesbackend.constants.Estatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lotes.lotesbackend.entity.Proyecto;

import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    List<Proyecto> getProyectosByEstatus(Estatus estatus);

}
