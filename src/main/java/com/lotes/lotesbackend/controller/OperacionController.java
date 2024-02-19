package com.lotes.lotesbackend.controller;

import com.lotes.lotesbackend.constants.TipoEntidad;
import com.lotes.lotesbackend.constants.TipoIndicador;
import com.lotes.lotesbackend.constants.TipoOperacion;
import com.lotes.lotesbackend.dto.CotaDTO;
import com.lotes.lotesbackend.dto.IndicadorOperacionDTO;
import com.lotes.lotesbackend.dto.OperacionDTO;
import com.lotes.lotesbackend.entity.IndicadorOperacion;
import com.lotes.lotesbackend.entity.Operacion;
import com.lotes.lotesbackend.repository.OperacionRepository;
import com.lotes.lotesbackend.service.OperacionService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/operacion")
public class OperacionController {

    @Autowired
    OperacionService operacionService;

    @PostMapping
    public ResponseEntity<?> create(@RequestParam(name = "tipoOperacionId") Integer tipoOperacionId,
                                    @RequestParam(name = "tipoEntidadId") Integer tipoEntidadId,
                                    @RequestParam(name = "usuarioId") Long usuarioId,
                                    @RequestParam(name = "datos") String datos
                                    ){
        Long operacionId = this.operacionService.saveOperacion(TipoOperacion.of(tipoOperacionId), TipoEntidad.of(tipoEntidadId), usuarioId, datos);
        return ResponseEntity.ok(operacionId);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<OperacionDTO> operaciones =  this.operacionService.findAll();
        return ResponseEntity.ok(operaciones);
    }

    @GetMapping(value = "/indicadores")
    public ResponseEntity<?> getIndicadores(@Nullable @RequestParam(name = "usuarioId", required = false) Long usuarioId,
                                            @RequestParam(name = "tipoindicador") Integer tipoIndicador){
        List<IndicadorOperacionDTO> indicadores = new ArrayList<>();
        indicadores = this.operacionService.getIndicadoresOperaciones(usuarioId, TipoIndicador.of(tipoIndicador));

        return ResponseEntity.ok(indicadores);
    }


}
