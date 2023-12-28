package com.lotes.lotesbackend.controller;

import com.lotes.lotesbackend.constants.Orientacion;
import com.lotes.lotesbackend.constants.TipoLinea;
import com.lotes.lotesbackend.dto.CotaDTO;
import com.lotes.lotesbackend.dto.FraccionDTO;
import com.lotes.lotesbackend.service.CotaService;
import com.lotes.lotesbackend.service.FraccionService;
import com.lotes.lotesbackend.service.ProyectoService;
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

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<CotaDTO> cotaList = cotaService.findAll();
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
    public ResponseEntity<?> create(@RequestBody CotaDTO fraccionDTO){

        CotaDTO cota = new CotaDTO();
        //cota.setFraccion(frac); //Fraccion a la que pertenece
        cota.setOrden(1);
        cota.setOrientacion(Orientacion.ESTE);
        cota.setMedida(new BigDecimal(19.5));
        cota.setTipoLinea(TipoLinea.RECTA);

        List<FraccionDTO> colindancias = new ArrayList<>();
        //colindancias.add(frac2);
        cota.setColindancias(colindancias);

        cota = cotaService.save(cota);



        return ResponseEntity.status(HttpStatus.CREATED).body(cota);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CotaDTO cotDTO, @PathVariable("id") Long id){
        try {
            Optional<CotaDTO> resultCotaDTO = cotaService.findById(id);
            if(!resultCotaDTO.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            //Se convierte el fraccionDTO encontrado en update
            //CotaDTO proyUpdate = modelMapper.map(cotDTO, resultCotaDTO.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(cotaService.update(cotDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }
}
