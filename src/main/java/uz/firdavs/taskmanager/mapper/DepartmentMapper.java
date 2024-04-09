package uz.firdavs.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.firdavs.taskmanager.entity.Department;
import uz.firdavs.taskmanager.mapper.base.BaseMapper;
import uz.firdavs.taskmanager.payload.rs.DepartmentRsDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface DepartmentMapper extends BaseMapper<DepartmentRsDto, Department> {
    @Override
    @Mapping(target = "director_id", source = "director.id")
    @Mapping(target = "director_fio", source = "director.fio")
    public DepartmentRsDto toResponse(Department department) ;

    @Override
    public List<DepartmentRsDto> toResponseList(List<Department> departments);
}
