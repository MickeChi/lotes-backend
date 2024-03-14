package com.lotes.lotesbackend.controller;

import com.lotes.lotesbackend.constants.Estatus;
import com.lotes.lotesbackend.constants.Orientacion;
import com.lotes.lotesbackend.constants.TipoLinea;
import com.lotes.lotesbackend.dto.CotaDTO;
import com.lotes.lotesbackend.dto.FraccionDTO;
import com.lotes.lotesbackend.service.CotaService;
import com.lotes.lotesbackend.service.FraccionService;
import com.lotes.lotesbackend.service.ProyectoService;
import jakarta.annotation.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cota")
public class CotaController {
    @Autowired
    FraccionService fraccionService;

    @Autowired
    ProyectoService proyectoService;

    @Autowired
    CotaService cotaService;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(name = "fraccionId") Long fraccionId,
                                     @Nullable @RequestParam(value = "estatus", required = false) Integer status){
        List<CotaDTO> cotaList = new ArrayList<>();
        if(fraccionId != null){
            cotaList = cotaService.getCotasByFraccionId(fraccionId);
        }else{
            cotaList = cotaService.findAll(status);
        }

        return ResponseEntity.ok(cotaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        Optional<CotaDTO> fraccionOp = cotaService.findById(id);
        if(!fraccionOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(fraccionOp.get());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CotaDTO cotaDTO){
        cotaDTO = cotaService.save(cotaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cotaDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CotaDTO cotDTO, @PathVariable("id") Long id){
        try {
            Optional<CotaDTO> resultCotaDTO = cotaService.findById(id);
            if(!resultCotaDTO.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(cotaService.update(cotDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<CotaDTO> cotaOp = cotaService.findById(id);
        if(!cotaOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(cotaService.delete(id));
    }
}
