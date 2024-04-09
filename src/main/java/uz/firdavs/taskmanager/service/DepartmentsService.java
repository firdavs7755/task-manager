package uz.firdavs.taskmanager.service;

 import uz.firdavs.taskmanager.dto.ResponseDto;
 import uz.firdavs.taskmanager.payload.rq.DepartmentRqDto;
 import uz.firdavs.taskmanager.payload.rq.NameRq;
 import uz.firdavs.taskmanager.service.base.BaseService;

public interface DepartmentsService extends BaseService {
    ResponseDto<?> selectEmpsSectionByDepar();
    ResponseDto<?> createRow(DepartmentRqDto req);
    ResponseDto<?> editRowById(DepartmentRqDto req, Integer id);
}
