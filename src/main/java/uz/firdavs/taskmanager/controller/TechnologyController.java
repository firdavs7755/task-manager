package uz.firdavs.taskmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.rq.TechnologyRqDto;
import uz.firdavs.taskmanager.service.TechnologiesService;

import java.util.Map;

@RestController
@RequestMapping("/technologies")
@RequiredArgsConstructor
public class TechnologyController {


    private final TechnologiesService service;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<ResponseDto<?>> findAll(@RequestParam Map<String,Object> map){
        return ResponseEntity.ok(service.findAll(map));
    }
    /*
    @GetMapping(value = "/by-part/{tech_part_id}")
    public ResponseEntity<ResponseDto<?>> getalltechnologiesByTechPart(@PathVariable Integer tech_part_id){
        return ResponseEntity.ok(service.getTechnologyByTechPartId(tech_part_id));
    }
    */

    @GetMapping(value = "/{id}")
    @Operation(summary = "get row by id")
    public ResponseEntity<ResponseDto<?>> getRowById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getRowById(id));
    }
    @PostMapping
    @Operation(summary = "create row")
    public ResponseEntity<ResponseDto<?>> createRow(@RequestBody TechnologyRqDto technologyRqDto){
        return ResponseEntity.ok(service.createRow(technologyRqDto));
    }
    @PutMapping(value = "/{id}")
    @Operation(summary = "edit row by id")
    public ResponseEntity<ResponseDto<?>> editRowById(@RequestBody TechnologyRqDto technologyRqDto, @PathVariable Integer id){
        return ResponseEntity.ok(service.editRowById(technologyRqDto,id));
    }
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "delete row by id")
    public ResponseEntity<ResponseDto<?>> deleteRowById(@PathVariable Integer id){
        return ResponseEntity.ok(service.deleteRowById(id));
    }

}
