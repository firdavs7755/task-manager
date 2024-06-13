package uz.firdavs.taskmanager.service;

import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.rq.TopRqDto;
import uz.firdavs.taskmanager.service.base.BaseService;

public interface TopService extends BaseService {
    ResponseDto<?> selectTopLike();
    ResponseDto<?> createRow(TopRqDto req);
    ResponseDto<?> editRowById(TopRqDto req, Integer id);
}
