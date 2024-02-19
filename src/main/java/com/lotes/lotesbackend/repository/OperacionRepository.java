package com.lotes.lotesbackend.repository;

import com.lotes.lotesbackend.entity.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperacionRepository extends JpaRepository<Operacion, Long>, CustomOperacionRepository {

}
