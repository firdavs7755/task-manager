package uz.firdavs.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.req.ReqName;
import uz.firdavs.taskmanager.service.DepartmentsService;
import uz.firdavs.taskmanager.service.TechnologiesService;

@RestController
@RequestMapping("/departments")
//@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 8080)
public class DepartmentsController {

    @Autowired
    DepartmentsService service;

    @GetMapping
    public HttpEntity<ResponseDto> getallDepartment(){
        return ResponseEntity.ok(service.getDepartments());
    }
    @GetMapping(value = "/{id}")
    public HttpEntity<ResponseDto> getallDepartment(@PathVariable Integer id){
        return ResponseEntity.ok(service.getDepartmentById(id));
    }
    @GetMapping(value = "/report")
    public HttpEntity<ResponseDto> getEmpsSectinByDepar(){
        return ResponseEntity.ok(service.selectEmpsSectionByDepar());
    }
    @PostMapping
    public HttpEntity<ResponseDto> createDepartment(@RequestBody ReqName reqName){
        return ResponseEntity.ok(service.createDepartment(reqName));
    }
    @PutMapping(value = "/{id}")
    public HttpEntity<ResponseDto> updateDepartment(@RequestBody ReqName reqName, @PathVariable Integer id){
        return ResponseEntity.ok(service.editDepartmentById(reqName,id));
    }
    @DeleteMapping(value = "/{id}")
    public HttpEntity<ResponseDto> deleteDepartment(@PathVariable Integer id){
        return ResponseEntity.ok(service.deleteDepartmentById(id));
    }

}
