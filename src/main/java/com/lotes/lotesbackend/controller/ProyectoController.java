package com.lotes.lotesbackend.controller;

import com.lotes.lotesbackend.dto.ProyectoDTO;
import com.lotes.lotesbackend.service.ProyectoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proyecto")
public class ProyectoController {
    @Autowired
    ProyectoService proyectoService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<ProyectoDTO> proyectosList = proyectoService.findAll();
        return ResponseEntity.ok(proyectosList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        Optional<ProyectoDTO> proyectoOp = proyectoService.findById(id);
        if(!proyectoOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(proyectoOp.get());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProyectoDTO proyectoDTO){
        ProyectoDTO proyecto = proyectoService.save(proyectoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(proyecto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ProyectoDTO proyDTO, @PathVariable("id") Long id){
        try {
            Optional<ProyectoDTO> resultProyDTO = proyectoService.findById(id);
            if(!resultProyDTO.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            //Se convierte el proyectoDTO encontrado en update
            //ProyectoDTO proyUpdate = modelMapper.map(proyDTO, resultProyDTO.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(proyectoService.update(proyDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }
}
