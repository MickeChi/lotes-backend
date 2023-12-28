package com.lotes.lotesbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lotes.lotesbackend.entity.Proyecto;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

}
