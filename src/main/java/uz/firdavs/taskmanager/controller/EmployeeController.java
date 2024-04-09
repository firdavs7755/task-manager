package uz.firdavs.taskmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.rq.EmployeeRqDto;
import uz.firdavs.taskmanager.service.EmployeesService;

import javax.transaction.Transactional;
import java.util.Map;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {


   private final EmployeesService service;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<ResponseDto<?>> findAll(@RequestParam Map<String,Object> map){
        return ResponseEntity.ok(service.selectEmployees());
    }
    @GetMapping(value = "/{id}")
    @Operation(summary = "get row by id")
    public ResponseEntity<ResponseDto<?>> getRowById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getRowById(id));
    }
    @PostMapping
    @Operation(summary = "create row")
    public ResponseEntity<ResponseDto<?>> createRow(@RequestBody EmployeeRqDto employeeRqDto){
        return ResponseEntity.ok(service.createRow(employeeRqDto));
    }
    @PutMapping(value = "/{id}")
    @Operation(summary = "edit row by id")
    public ResponseEntity<ResponseDto<?>> editRowById(@RequestBody EmployeeRqDto employeeRqDto, @PathVariable Integer id){
        return ResponseEntity.ok(service.editRowById(employeeRqDto,id));
    }
    @Transactional
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "delete row by id")
    public ResponseEntity<ResponseDto<?>> deleteRowById(@PathVariable Integer id){
        return ResponseEntity.ok(service.deleteRowById(id));
    }

}
