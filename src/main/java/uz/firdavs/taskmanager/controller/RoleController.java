package uz.firdavs.taskmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.rq.DepartmentRqDto;
import uz.firdavs.taskmanager.service.DepartmentsService;
import uz.firdavs.taskmanager.service.RoleService;

import java.util.Map;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {


    private final RoleService service;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<ResponseDto<?>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/by_user_id/{id}")
    @Operation(summary = "selectRolesByUserId")
    public ResponseEntity<ResponseDto<?>> selectRolesByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(service.selectRolesByUserId(id));
    }
}
