package uz.firdavs.taskmanager.service;

 import uz.firdavs.taskmanager.dto.ResponseDto;
 import uz.firdavs.taskmanager.req.ReqEmployees;
 import uz.firdavs.taskmanager.req.ReqName;

public interface EmployeesService {
    ResponseDto<?> getEmployees();
    ResponseDto<?> getEmployeeById(Integer id);
    ResponseDto<?> createEmployee(ReqEmployees reqEmployees);
    ResponseDto<?> editEmployeeById(ReqEmployees reqEmployees, Integer id);
    ResponseDto<?> deleteEmployeeById(Integer id);
}
