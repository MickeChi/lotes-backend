package com.lotes.lotesbackend.controller;

import com.lotes.lotesbackend.constants.Orientacion;
import com.lotes.lotesbackend.constants.TipoColindancia;
import com.lotes.lotesbackend.constants.TipoLinea;
import com.lotes.lotesbackend.dto.CotaDTO;
import com.lotes.lotesbackend.dto.FraccionDTO;
import com.lotes.lotesbackend.dto.ProyectoDTO;
import com.lotes.lotesbackend.service.CotaService;
import com.lotes.lotesbackend.service.FraccionService;
import com.lotes.lotesbackend.service.ProyectoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/fraccion")
public class FraccionController {
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
        List<FraccionDTO> fraccionsList = fraccionService.findAll();
        return ResponseEntity.ok(fraccionsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        Optional<FraccionDTO> fraccionOp = fraccionService.findById(id);
        if(!fraccionOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(fraccionOp.get());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody FraccionDTO fraccionDTO){

        FraccionDTO frac = new FraccionDTO();
        frac.setFraccion(1);
        frac.setColonia("dos por 3");
        frac.setClase("lote rustico");
        frac.setDescripcion("sdfs dfds wervef er er e");
        frac.setFinca("merida");
        frac.setTipoColindancia(TipoColindancia.LOTE);
        Optional<ProyectoDTO> proyOp = proyectoService.findById(1L);
        if(proyOp.isPresent()){
            fraccionDTO.setProyecto(proyOp.get());
        }

        frac = fraccionService.save(frac);

        FraccionDTO frac2 = new FraccionDTO();
        frac2.setFraccion(2);
        frac2.setColonia("dos por 3");
        frac2.setClase("lote rustico");
        frac2.setDescripcion("sdfs dfds wervef er er e");
        frac2.setFinca("merida");
        frac2.setTipoColindancia(TipoColindancia.LOTE);
        if(proyOp.isPresent()){
            frac2.setProyecto(proyOp.get());
        }
        frac2 = fraccionService.save(frac2);

        /*
        CotaDTO cota = new CotaDTO();
        cota.setFraccion(frac); //Fraccion a la que pertenece
        cota.setOrden(1);
        cota.setOrientacion(Orientacion.ESTE);
        cota.setMedida(new BigDecimal(19.5));
        cota.setTipoLinea(TipoLinea.RECTA);

        List<FraccionDTO> colindancias = new ArrayList<>();
        colindancias.add(frac2);
        cota.setColindancias(colindancias);

        cota = cotaService.save(cota);
        List<CotaDTO> cotas = new ArrayList<>();
        cotas.add(cota);
        frac.setCotas(cotas);

         */

        return ResponseEntity.status(HttpStatus.CREATED).body(frac);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody FraccionDTO proyDTO, @PathVariable("id") Long id){
        try {
            Optional<FraccionDTO> resultProyDTO = fraccionService.findById(id);
            if(!resultProyDTO.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            //Se convierte el fraccionDTO encontrado en update
            //FraccionDTO proyUpdate = modelMapper.map(proyDTO, resultProyDTO.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(fraccionService.update(proyDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }
}
