package uz.firdavs.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.EmployeesTechnologies;
import uz.firdavs.taskmanager.repository.EmployeesTechnologiesRepository;
import uz.firdavs.taskmanager.req.ReqEmployees;
import uz.firdavs.taskmanager.service.EmployeesService;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/employees")
//@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 8080)
public class EmployeesController {

    @Autowired
    EmployeesService service;
    @Autowired
    EmployeesTechnologiesRepository employeesTechnologiesRepository;

    @GetMapping
    public HttpEntity<ResponseDto> getallEmployee(){
        return ResponseEntity.ok(service.getEmployees());
    }
    @GetMapping(value = "/{id}")
    public HttpEntity<ResponseDto> getallEmployee(@PathVariable Integer id){
        return ResponseEntity.ok(service.getEmployeeById(id));
    }
    @PostMapping
    public HttpEntity<ResponseDto> createEmployee(@RequestBody ReqEmployees reqEmployees){
        return ResponseEntity.ok(service.createEmployee(reqEmployees));
    }
    @PutMapping(value = "/{id}")
    public HttpEntity<ResponseDto> updateEmployee(@RequestBody ReqEmployees reqEmployees, @PathVariable Integer id){
        return ResponseEntity.ok(service.editEmployeeById(reqEmployees,id));
    }
    @Transactional
    @DeleteMapping(value = "/{id}")
    public HttpEntity<ResponseDto> deleteEmployee(@PathVariable Integer id){
        employeesTechnologiesRepository.deleteByEmp_id(id);
        service.deleteEmployeeById(id);
        return ResponseEntity.status(200).body(new ResponseDto(true,"deleted !"));
    }

}
