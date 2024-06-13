package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.mapper.EmployeeTypeMapper;
import uz.firdavs.taskmanager.mapper.WishMapper;
import uz.firdavs.taskmanager.repository.EmployeeTypeRepository;
import uz.firdavs.taskmanager.repository.WishRepository;
import uz.firdavs.taskmanager.service.EmployeeTypeService;
import uz.firdavs.taskmanager.service.WishService;
import uz.firdavs.taskmanager.utis.Utils;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImplEmployeeType implements EmployeeTypeService {
    private final EmployeeTypeRepository repository;
    private final EmployeeTypeMapper mapper;


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
