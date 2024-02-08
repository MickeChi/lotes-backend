package com.lotes.lotesbackend.repository;

import com.lotes.lotesbackend.entity.Fraccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lotes.lotesbackend.entity.Cota;

import java.util.List;

@Repository
public interface CotaRepository extends JpaRepository<Cota, Long> {
    List<Cota> getCotasByFraccionId(Long fraccionid);
}
