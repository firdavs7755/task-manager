package uz.firdavs.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.req.ReqTechnologies;
import uz.firdavs.taskmanager.service.TechnologiesService;

@RestController
@RequestMapping("/technologies")
@CrossOrigin(origins = "*", maxAge = 8080)
public class TechnologiesController  {

    @Autowired
    TechnologiesService service;

    @GetMapping
    public HttpEntity<ResponseDto> getalltechnologies(){
        return ResponseEntity.ok(service.getTechnologies());
    }
    @GetMapping(value = "/by-part/{tech_part_id}")
    public HttpEntity<ResponseDto> getalltechnologiesByTechPart(@PathVariable Integer tech_part_id){
        return ResponseEntity.ok(service.getTechnologyByTechPartId(tech_part_id));
    }
    @GetMapping(value = "/{id}")
    public HttpEntity<ResponseDto> getalltechnologiesByid(@PathVariable Integer id){
        return ResponseEntity.ok(service.getTechnologyById(id));
    }
    @PostMapping
    public HttpEntity<ResponseDto> createTechnology(@RequestBody ReqTechnologies reqTechnologies){
        return ResponseEntity.ok(service.createTechnology(reqTechnologies));
    }
    @PutMapping(value = "/{id}")
    public HttpEntity<ResponseDto> updateTechnology(@RequestBody ReqTechnologies reqTechnologies, @PathVariable Integer id){
        return ResponseEntity.ok(service.editTechnologyById(reqTechnologies,id));
    }
    @DeleteMapping(value = "/{id}")
    public HttpEntity<ResponseDto> deleteTechnology(@PathVariable Integer id){
        return ResponseEntity.ok(service.deleteTechnologyById(id));
    }

}
