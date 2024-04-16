package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Director;
import uz.firdavs.taskmanager.entity.Wish;
import uz.firdavs.taskmanager.mapper.DirectorMapper;
import uz.firdavs.taskmanager.mapper.WishMapper;
import uz.firdavs.taskmanager.payload.rq.DirectorRqDto;
import uz.firdavs.taskmanager.repository.DirectorRepository;
import uz.firdavs.taskmanager.repository.WishRepository;
import uz.firdavs.taskmanager.service.DirectorService;
import uz.firdavs.taskmanager.service.WishService;
import uz.firdavs.taskmanager.specifications.DirectorSpecification;
import uz.firdavs.taskmanager.utis.Utils;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImplWish implements WishService {
    private final WishRepository repository;
    private final WishMapper mapper;


    @Override
    public ResponseDto<?> findAll(Map<String, Object> map) {

        return Utils.generatePageable(repository,mapper, map);
    }

    @Override
    public ResponseDto<?> getRowById(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<?> deleteRowById(Integer id) {
        return null;
    }

}
