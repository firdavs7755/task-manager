package uz.firdavs.taskmanager.service;

 import uz.firdavs.taskmanager.dto.ResponseDto;
 import uz.firdavs.taskmanager.payload.rq.EmployeeRqDto;
 import uz.firdavs.taskmanager.service.base.BaseService;

 import java.util.Map;

public interface EmployeesService extends BaseService {
    ResponseDto<?> selectEmployees(Map<String,Object> map);
    ResponseDto<?> createRow(EmployeeRqDto employeeRqDto);
    ResponseDto<?> editRowById(EmployeeRqDto employeeRqDto, Integer id);
}
