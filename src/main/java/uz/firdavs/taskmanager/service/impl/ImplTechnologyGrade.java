package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.mapper.TechnologyGradeMapper;
import uz.firdavs.taskmanager.repository.TechnologyGradeRepository;
import uz.firdavs.taskmanager.service.TechnologyGradeService;
import uz.firdavs.taskmanager.utis.Utils;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImplTechnologyGrade implements TechnologyGradeService {
    private final TechnologyGradeRepository repository;
    private final TechnologyGradeMapper mapper;


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
