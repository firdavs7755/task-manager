package uz.firdavs.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.firdavs.taskmanager.entity.Employee;
import uz.firdavs.taskmanager.mapper.base.BaseMapper;
import uz.firdavs.taskmanager.payload.rs.EmployeeRsDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface EmployeeMapper extends BaseMapper<EmployeeRsDto, Employee> {
    @Override
    @Mapping(target = "created_user_id", source = "created_user.id")
    @Mapping(target = "created_user_fio", source = "created_user.fio")
    @Mapping(target = "department_id", source = "department.id")
    @Mapping(target = "department_name", source = "department.name")
    @Mapping(target = "wish_id", source = "wish.id")
    @Mapping(target = "wish_name", source = "wish.name")
    public EmployeeRsDto toResponse(Employee employee) ;

    @Override
    public List<EmployeeRsDto> toResponseList(List<Employee> departments);
}
