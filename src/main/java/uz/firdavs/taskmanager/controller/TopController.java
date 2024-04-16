package uz.firdavs.taskmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.rq.TopRqDto;
import uz.firdavs.taskmanager.service.TopService;

import java.util.Map;

@RestController
@RequestMapping("/top")
@RequiredArgsConstructor
public class TopController {

    private final TopService service;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<ResponseDto<?>> findAll(@RequestParam Map<String,Object> map){
        return ResponseEntity.ok(service.findAll(map));
    }
    @GetMapping(value = "/{id}")
    @Operation(summary = "get row by id")
    public ResponseEntity<ResponseDto<?>> getRowById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getRowById(id));
    }

    @PostMapping
    @Operation(summary = "create row")
    public ResponseEntity<ResponseDto<?>> createRow(@RequestBody TopRqDto req){
        return ResponseEntity.ok(service.createRow(req));
    }
    @PutMapping(value = "/{id}")
    @Operation(summary = "edit row by id")
    public ResponseEntity<ResponseDto<?>> editRowById(@RequestBody TopRqDto req, @PathVariable Integer id){
        return ResponseEntity.ok(service.editRowById(req,id));
    }
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "delete row by id")
    public ResponseEntity<ResponseDto<?>> deleteRowById(@PathVariable Integer id){
        return ResponseEntity.ok(service.deleteRowById(id));
    }

}