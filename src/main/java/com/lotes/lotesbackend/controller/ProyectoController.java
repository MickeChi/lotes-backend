package com.lotes.lotesbackend.controller;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.ULocale;
import com.lotes.lotesbackend.dto.CotaProyectoDTO;
import com.lotes.lotesbackend.dto.ProyectoDTO;
import com.lotes.lotesbackend.service.ProyectoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    
    @PostMapping("/cota-proyecto")
    public ResponseEntity<?> createCotaProyecto(@RequestBody CotaProyectoDTO cotaProyectoDTO){
    	
    	
    	CotaProyectoDTO cotaProyecto = proyectoService.saveCotaProyecto(cotaProyectoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cotaProyecto);

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

    @GetMapping("/generate-fracciones-doc/{id}")
    public ResponseEntity<?> generateFraccionesDoc(@PathVariable("id") Long id){

        ULocale to = new ULocale("es_MX");
        String numberTxt = "";
        try {
            /*Double number = Double.parseDouble(numero);
            RuleBasedNumberFormat rbnf = new RuleBasedNumberFormat(to, RuleBasedNumberFormat.SPELLOUT);
            numberTxt = rbnf.format(number);*/
            Map<String, String> resp = new HashMap<>();
//            resp.put(numero, numberTxt);
            return ResponseEntity.ok(resp);

        } catch(Throwable t) {
            return ResponseEntity.badRequest().build();
        }


    }
}
