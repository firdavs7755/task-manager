package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.mapper.RoleMapper;
import uz.firdavs.taskmanager.repository.RoleRepository;
import uz.firdavs.taskmanager.service.RoleService;
import uz.firdavs.taskmanager.utis.Utils;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImplRole implements RoleService {
    private final RoleRepository repository;
    private final RoleMapper mapper;


    @Override
    public ResponseDto<?> findAll() {
        return Utils.generatePageable(repository, mapper);
    }

    @Override
    public ResponseDto<?> selectRolesByUserId(Integer userId) {
        List<Map<String, Object>> list = repository.selectRolesByUserId(userId);
        if (list.size()>0){
            return new ResponseDto<>(true,"ok",list);
        }
        return new ResponseDto<>(true,"no data");
    }
}
