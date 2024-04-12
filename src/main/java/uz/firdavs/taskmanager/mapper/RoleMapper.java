package uz.firdavs.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.firdavs.taskmanager.entity.Role;
import uz.firdavs.taskmanager.mapper.base.BaseMapper;
import uz.firdavs.taskmanager.payload.rs.RoleRsDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface RoleMapper extends BaseMapper<RoleRsDto, Role> {


    @Override
    @Mapping(target = "name", source = "role")
    public RoleRsDto toResponse(Role item) ;

    @Override
    public List<RoleRsDto> toResponseList(List<Role> items);
}
