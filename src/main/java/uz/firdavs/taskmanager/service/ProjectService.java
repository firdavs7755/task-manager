package uz.firdavs.taskmanager.service;

import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.rq.NameRq;
import uz.firdavs.taskmanager.service.base.BaseService;

public interface ProjectService extends BaseService {
    ResponseDto<?> createRow(NameRq req);
    ResponseDto<?> editRowById(NameRq req, Integer id);
}
