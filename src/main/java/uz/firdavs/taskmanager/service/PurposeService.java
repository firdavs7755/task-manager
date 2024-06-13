package uz.firdavs.taskmanager.service;

import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.rq.PurposeRqDto;
import uz.firdavs.taskmanager.service.base.BaseService;

import java.util.Map;

public interface PurposeService extends BaseService {
    ResponseDto<?> selectPurpose(Map<String,Object> map);

    ResponseDto<?> createRow(PurposeRqDto req);
    ResponseDto<?> editRowById(PurposeRqDto req, Integer id);
}
