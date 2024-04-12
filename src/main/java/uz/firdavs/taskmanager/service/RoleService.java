package uz.firdavs.taskmanager.service;

import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.rq.DepartmentRqDto;
import uz.firdavs.taskmanager.service.base.BaseService;

public interface RoleService {
    ResponseDto<?> findAll();
    ResponseDto<?> selectRolesByUserId(Integer userId);
}
