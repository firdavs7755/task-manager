package uz.firdavs.taskmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.rq.TopLikeRqDto;
import uz.firdavs.taskmanager.service.TopLikeService;

@RestController
@RequestMapping("/top-like")
@RequiredArgsConstructor
public class TopLikeController {

    private final TopLikeService service;


    @PostMapping
    @Operation(summary = "create row")
    public ResponseEntity<ResponseDto<?>> createRow(@RequestBody TopLikeRqDto req){
        return ResponseEntity.ok(service.createRow(req));
    }

}
