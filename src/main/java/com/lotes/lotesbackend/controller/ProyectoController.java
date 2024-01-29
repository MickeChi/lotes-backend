package com.lotes.lotesbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.ULocale;
import com.lotes.lotesbackend.dto.FileInfoDTO;
import com.lotes.lotesbackend.dto.FraccionExternaDTO;
import com.lotes.lotesbackend.dto.ProyectoDTO;
import com.lotes.lotesbackend.dto.ProyectoTextoDTO;
import com.lotes.lotesbackend.dto.ResponseMessage;
import com.lotes.lotesbackend.service.CotaService;
import com.lotes.lotesbackend.service.FileStorageService;
import com.lotes.lotesbackend.service.FraccionService;
import com.lotes.lotesbackend.service.ProyectoService;
import com.lotes.lotesbackend.utils.NumberUtils;

import jakarta.annotation.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
    
    @Autowired
    FileStorageService storageService;

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
            @Nullable @RequestParam(value = "documento",required = false) MultipartFile documento){
    	
    	String fileName = documento.getOriginalFilename();    	
        try {
        	
        	documento.transferTo(new File("C:\\upload\\" + fileName));
        	
        	// create object of Path 
            Path path = (Path)Paths.get("//upload2", "macp", "bin"); 
      
            // print Path 
            System.out.println(path); 

        	/*
            Optional<ProyectoDTO> resultProyDTO = proyectoService.findById(id);
            if(!resultProyDTO.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            ObjectMapper mapper = new ObjectMapper();
            ProyectoDTO proyDTO = mapper.readValue(proyectoJson, ProyectoDTO.class);*/

        	return ResponseEntity.status(HttpStatus.CREATED).body(new ProyectoDTO());
            //return ResponseEntity.status(HttpStatus.CREATED).body(proyectoService.update(proyDTO));
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
    
    @GetMapping("/test-numberstxt")
    public ResponseEntity<?> testNumbersFunctions(@RequestParam("texto") String texto){

        String textoNumeros = NumberUtils.allNumbersToText(texto);
        Map<String, String> resultados = new HashMap<>();
        resultados.put(texto, textoNumeros);
        return ResponseEntity.ok(resultados);

    }
    
    
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
      String message = "";
      try {
        storageService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
      }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfoDTO>> getListFiles() {
      List<FileInfoDTO> fileInfos = storageService.loadAll().map(path -> {
        String filename = path.getFileName().toString();
        String url = MvcUriComponentsBuilder
            .fromMethodName(ProyectoController.class, "getFile", path.getFileName().toString()).build().toString();

        return new FileInfoDTO(filename, url);
      }).collect(Collectors.toList());

      return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
      Resource file = storageService.load(filename);
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    
    //https://copyprogramming.com/howto/how-to-display-files-from-external-folder-with-spring-boot-2-1-1
    //https://copyprogramming.com/howto/include-an-external-files-in-spring-boot-jar
    //https://www.bezkoder.com/spring-boot-file-upload/
    //https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/file-upload-Spring-Boot-Ajax-example
    //save file in tomcat directory spring boot
    //https://www.codejava.net/frameworks/spring-boot/file-download-upload-rest-api-examples
    
    
    
}
