package uz.firdavs.taskmanager.service;

import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.rq.CourseRqDto;
import uz.firdavs.taskmanager.service.base.BaseService;

public interface CourseService extends BaseService {
    ResponseDto<?> createRow(CourseRqDto req);
    ResponseDto<?> editRowById(CourseRqDto req, Integer id);
}
