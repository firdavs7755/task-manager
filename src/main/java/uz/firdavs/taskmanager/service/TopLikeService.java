package uz.firdavs.taskmanager.service;

import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.rq.TopLikeRqDto;
import uz.firdavs.taskmanager.service.base.BaseService;

public interface TopLikeService extends BaseService {
    ResponseDto<?> createRow(TopLikeRqDto req);
    ResponseDto<?> editRowById(TopLikeRqDto req, Integer id);
}
