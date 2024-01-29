package com.lotes.lotesbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.ULocale;
import com.lotes.lotesbackend.dto.FraccionExternaDTO;
import com.lotes.lotesbackend.dto.ProyectoDTO;
import com.lotes.lotesbackend.dto.ProyectoTextoDTO;
import com.lotes.lotesbackend.service.CotaService;
import com.lotes.lotesbackend.service.FraccionService;
import com.lotes.lotesbackend.service.ProyectoService;
import jakarta.annotation.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/proyecto")
public class ProyectoController {
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
    public ResponseEntity<?> create(
            @RequestParam("proyecto") String proyectoJson,
            @Nullable @RequestParam(value = "documento",required = false) MultipartFile documento
            //@RequestBody ProyectoDTO proyectoDTO
    ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ProyectoDTO proyectoDTO = mapper.readValue(proyectoJson, ProyectoDTO.class);

        ProyectoDTO proyecto = proyectoService.save(proyectoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(proyecto);

    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @RequestParam("proyecto") String proyectoJson,
            @Nullable @RequestParam(value = "documento",required = false) MultipartFile documento
            //@RequestBody ProyectoDTO proyDTO, @PathVariable("id") Long id
    ){
        try {

            Optional<ProyectoDTO> resultProyDTO = proyectoService.findById(id);
            if(!resultProyDTO.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            ObjectMapper mapper = new ObjectMapper();
            ProyectoDTO proyDTO = mapper.readValue(proyectoJson, ProyectoDTO.class);

            return ResponseEntity.status(HttpStatus.CREATED).body(proyectoService.update(proyDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/fraccion-externa")
    public ResponseEntity<?> createFraccionExterna(@RequestBody FraccionExternaDTO fraccionExternaDTO){


        FraccionExternaDTO fraccionExterna = proyectoService.saveFraccionExterna(fraccionExternaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(fraccionExterna);

    }

    @GetMapping("/generate-document")
    public ResponseEntity<?> generateDocument(@RequestParam("id") Long id, @RequestParam("numero") String numero){

        ULocale to = new ULocale("es_MX");
        String numberTxt = "";
        try {
            Double number = Double.parseDouble(numero);
            RuleBasedNumberFormat rbnf = new RuleBasedNumberFormat(to, RuleBasedNumberFormat.SPELLOUT);
            numberTxt = rbnf.format(number);
            Map<String, String> resp = new HashMap<>();
            resp.put(numero, numberTxt);
            return ResponseEntity.ok(resp);

        } catch(Throwable t) {
            return ResponseEntity.badRequest().build();
        }


    }

    @GetMapping("/generate-proyecto-doc/{proyectoId}")
    public ResponseEntity<?> generateFraccionesDoc(@PathVariable("proyectoId") Long proyectoId){

        Optional<ProyectoDTO> proyectoOp = proyectoService.findById(proyectoId);
        if(!proyectoOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ProyectoTextoDTO proyectoTexto = this.proyectoService.generateFraccionesText(proyectoId);

        return ResponseEntity.ok(proyectoTexto);

    }
}
