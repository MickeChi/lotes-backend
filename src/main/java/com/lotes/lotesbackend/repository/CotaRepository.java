package com.lotes.lotesbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lotes.lotesbackend.entity.Cota;

@Repository
public interface CotaRepository extends JpaRepository<Cota, Long> {

}
