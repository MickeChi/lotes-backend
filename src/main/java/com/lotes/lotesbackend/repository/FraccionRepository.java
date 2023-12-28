package com.lotes.lotesbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lotes.lotesbackend.entity.Fraccion;

@Repository
public interface FraccionRepository extends JpaRepository<Fraccion, Long> {

}
