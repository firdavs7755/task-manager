package uz.firdavs.taskmanager.service;

import uz.firdavs.taskmanager.dto.ResponseDto;

import uz.firdavs.taskmanager.payload.rq.NewCourseApplicationRqDto;
import uz.firdavs.taskmanager.service.base.BaseService;

public interface NewCourseApplicationService extends BaseService {
    ResponseDto<?> createRow(NewCourseApplicationRqDto req);
    ResponseDto<?> editRowById(NewCourseApplicationRqDto req, Integer id);
}
