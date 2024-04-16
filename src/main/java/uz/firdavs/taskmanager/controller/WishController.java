package uz.firdavs.taskmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.service.WishService;

import java.util.Map;

@RestController
@RequestMapping("/wish")
@RequiredArgsConstructor
public class WishController {


    private final WishService service;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<ResponseDto<?>> findAll(@RequestParam Map<String, Object> map) {
        return ResponseEntity.ok(service.findAll(map));
    }

}
