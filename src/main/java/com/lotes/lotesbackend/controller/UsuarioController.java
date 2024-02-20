package com.lotes.lotesbackend.controller;

import com.lotes.lotesbackend.dto.UsuarioDTO;
import com.lotes.lotesbackend.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
//@CrossOrigin(originPatterns = "*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<UsuarioDTO> usuariosList = new ArrayList<>();
        
        	usuariosList = usuarioService.findAll();
       

        return ResponseEntity.ok(usuariosList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        Optional<UsuarioDTO> userOp = usuarioService.findById(id);
        if(!userOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userOp.get());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UsuarioDTO usuarioDTO){

        usuarioDTO = usuarioService.save(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UsuarioDTO userDTO, @PathVariable("id") Long id){
        try {
            Optional<UsuarioDTO> resultUsuarioDTO = usuarioService.findById(id);
            if(!resultUsuarioDTO.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.update(userDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }
}
