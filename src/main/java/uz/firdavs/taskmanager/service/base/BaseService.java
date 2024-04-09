package uz.firdavs.taskmanager.service.base;

import uz.firdavs.taskmanager.dto.ResponseDto;

import java.util.Map;

/**
 * created by: Firdavsbek
 * Date:       23.03.2024
 * Project:    task-manager
 */


public interface BaseService {
    ResponseDto<?> findAll(Map<String, Object> map);

    ResponseDto<?> getRowById(Integer id);

    ResponseDto<?> deleteRowById(Integer id);
}
