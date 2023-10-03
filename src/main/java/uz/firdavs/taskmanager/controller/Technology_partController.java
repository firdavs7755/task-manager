package uz.firdavs.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Technology_part;
import uz.firdavs.taskmanager.req.ReqName;
import uz.firdavs.taskmanager.service.TechnologiesService;
import uz.firdavs.taskmanager.service.Technology_partService;

@RestController
@RequestMapping("/technology-part")
//@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 8080)
public class Technology_partController {

    @Autowired
    Technology_partService service;

//    @GetMapping
//    public HttpEntity<ResponseDto> getalltechnologies(){
//        return ResponseEntity.ok(service.getTechnology_part());
//    }

    @GetMapping
    public HttpEntity<ResponseDto> getalltechnologiesFilter(
                                        @RequestParam(required = false) String name,
                                        @RequestParam(required = false) Integer id
    ){
        return ResponseEntity.ok(service.getTechnology_partFilter(id,name));
    }
    @GetMapping(value = "/{id}")
    public HttpEntity<ResponseDto> getalltechnologies(@PathVariable Integer id){
        return ResponseEntity.ok(service.getTechnology_partById(id));
    }
    @GetMapping(value = "/report")
    public HttpEntity<ResponseDto> getalltechnologies(){
        return ResponseEntity.ok(service.selectEmpsSectionByTechPart());
    }
    @PostMapping
    public HttpEntity<ResponseDto> createTechnology(@RequestBody ReqName reqName){
        return ResponseEntity.ok(service.createTechnology_part(reqName));
    }
    @PutMapping(value = "/{id}")
    public HttpEntity<ResponseDto> updateTechnology(@RequestBody ReqName reqName, @PathVariable Integer id){
        return ResponseEntity.ok(service.editTechnology_partById(reqName,id));
    }
    @DeleteMapping(value = "/{id}")
    public HttpEntity<ResponseDto> deleteTechnology(@PathVariable Integer id){
        return ResponseEntity.ok(service.deleteTechnology_partById(id));
    }

}
