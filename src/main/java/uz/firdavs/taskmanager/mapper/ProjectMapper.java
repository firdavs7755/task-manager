package uz.firdavs.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.firdavs.taskmanager.entity.Project;
import uz.firdavs.taskmanager.entity.Top;
import uz.firdavs.taskmanager.mapper.base.BaseMapper;
import uz.firdavs.taskmanager.payload.rs.ProjectRsDto;
import uz.firdavs.taskmanager.payload.rs.TopRsDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface ProjectMapper extends BaseMapper<ProjectRsDto, Project> {
    @Override
    @Mapping(target = "created_user_id", source = "created_user.id")
    @Mapping(target = "created_user_name", source = "created_user.fio")
    public ProjectRsDto toResponse(Project item) ;

    @Override
    public List<ProjectRsDto> toResponseList(List<Project> items);
}
