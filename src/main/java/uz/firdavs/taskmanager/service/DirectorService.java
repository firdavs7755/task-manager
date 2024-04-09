package uz.firdavs.taskmanager.service;

import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.rq.DirectorRqDto;
import uz.firdavs.taskmanager.payload.rq.EmployeeRqDto;
import uz.firdavs.taskmanager.service.base.BaseService;

public interface DirectorService extends BaseService {
    ResponseDto<?> createRow(DirectorRqDto req);
    ResponseDto<?> editRowById(DirectorRqDto req, Integer id);
}
