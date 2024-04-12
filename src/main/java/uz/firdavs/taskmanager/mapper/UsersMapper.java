package uz.firdavs.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.firdavs.taskmanager.entity.Department;
import uz.firdavs.taskmanager.entity.Users;
import uz.firdavs.taskmanager.mapper.base.BaseMapper;
import uz.firdavs.taskmanager.payload.rs.DepartmentRsDto;
import uz.firdavs.taskmanager.payload.rs.UserRsDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface UsersMapper extends BaseMapper<UserRsDto, Users> {
    @Override
//    @Mapping(target = "password", source = "******")
    public UserRsDto toResponse(Users item) ;

    @Override
    public List<UserRsDto> toResponseList(List<Users> items);
}
