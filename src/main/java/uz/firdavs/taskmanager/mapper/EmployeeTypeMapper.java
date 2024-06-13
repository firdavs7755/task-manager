package uz.firdavs.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.firdavs.taskmanager.entity.EmployeeType;
import uz.firdavs.taskmanager.mapper.base.BaseMapper;
import uz.firdavs.taskmanager.payload.rs.base.BaseName;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface EmployeeTypeMapper extends BaseMapper<BaseName, EmployeeType> {

    @Override
    public BaseName toResponse(EmployeeType item) ;

    @Override
    public List<BaseName> toResponseList(List<EmployeeType> items);
}
