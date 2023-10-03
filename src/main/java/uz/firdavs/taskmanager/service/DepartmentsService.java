package uz.firdavs.taskmanager.service;

 import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.req.ReqName;

public interface DepartmentsService {
    ResponseDto<?> getDepartments();
    ResponseDto<?> getDepartmentById(Integer id);
    ResponseDto<?> selectEmpsSectionByDepar();
    ResponseDto<?> createDepartment(ReqName reqName);
    ResponseDto<?> editDepartmentById(ReqName reqName, Integer id);
    ResponseDto<?> deleteDepartmentById(Integer id);
}
