package uz.firdavs.taskmanager.controller;

import io.jsonwebtoken.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.rq.ReqAuth;
import uz.firdavs.taskmanager.payload.rq.ReqUser;
import uz.firdavs.taskmanager.payload.rq.TechnologyPartRqDto;
import uz.firdavs.taskmanager.service.UsersService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersResource {


    private final UsersService service;
    private static String secretKey="fir4gb56w4rbn6bhr46b41t6g4w3e5423t4ghtdavsokam354ax57545844w2d4qsutaliyev";



    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<ResponseDto<?>> findAll(@RequestParam Map<String,Object> map){
        return ResponseEntity.ok(service.findAll(map));
    }
    @PostMapping
    @Operation(summary = "create row")
    public ResponseEntity<ResponseDto<?>> createRow(@RequestBody ReqUser req){
        return ResponseEntity.ok(service.createRow(req));
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "edit row by id")
    public ResponseEntity<ResponseDto<?>> editRowById(@RequestBody ReqUser req, @PathVariable Integer id){
        return ResponseEntity.ok(service.updateUserById(req,id));
    }

}
