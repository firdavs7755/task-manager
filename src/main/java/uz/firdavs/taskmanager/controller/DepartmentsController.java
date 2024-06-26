package uz.firdavs.taskmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.rq.DepartmentRqDto;
import uz.firdavs.taskmanager.service.DepartmentsService;

import java.util.Map;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentsController {


    private final DepartmentsService service;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<ResponseDto<?>> findAll( @RequestParam Map<String, Object> map){
        return ResponseEntity.ok(service.findAll(map));
    }
    @GetMapping(value = "/{id}")
    @Operation(summary = "get row by id")
    public ResponseEntity<ResponseDto<?>> getRowById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getRowById(id));
    }
    @GetMapping(value = "/report")
    @Operation(summary = "selectEmpsSectionByDepar")
    public ResponseEntity<ResponseDto<?>> selectEmpsSectionByDepar(){
        return ResponseEntity.ok(service.selectEmpsSectionByDepar());
    }
    @PostMapping
    @Operation(summary = "create row")
    public ResponseEntity<ResponseDto<?>> createRow(@RequestBody DepartmentRqDto req){
        return ResponseEntity.ok(service.createRow(req));
    }
    @PutMapping(value = "/{id}")
    @Operation(summary = "edit row")
    public ResponseEntity<ResponseDto<?>> editRowById(@RequestBody DepartmentRqDto req, @PathVariable Integer id){
        return ResponseEntity.ok(service.editRowById(req,id));
    }
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "delete row")
    public ResponseEntity<ResponseDto<?>> deleteRowById(@PathVariable Integer id){
        return ResponseEntity.ok(service.deleteRowById(id));
    }

}
