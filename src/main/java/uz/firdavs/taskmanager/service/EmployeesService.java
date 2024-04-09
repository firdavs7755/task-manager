package uz.firdavs.taskmanager.service;

 import uz.firdavs.taskmanager.dto.ResponseDto;
 import uz.firdavs.taskmanager.payload.rq.EmployeeRqDto;
 import uz.firdavs.taskmanager.service.base.BaseService;

public interface EmployeesService extends BaseService {
    ResponseDto<?> selectEmployees();
    ResponseDto<?> createRow(EmployeeRqDto employeeRqDto);
    ResponseDto<?> editRowById(EmployeeRqDto employeeRqDto, Integer id);
}
