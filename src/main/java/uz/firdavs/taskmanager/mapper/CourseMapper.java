package uz.firdavs.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.firdavs.taskmanager.entity.Course;
import uz.firdavs.taskmanager.entity.Project;
import uz.firdavs.taskmanager.mapper.base.BaseMapper;
import uz.firdavs.taskmanager.payload.rs.CourseRsDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface CourseMapper extends BaseMapper<CourseRsDto, Course> {
    @Override
    @Mapping(target = "created_user_id", source = "created_user.id")
    @Mapping(target = "created_user_fio", source = "created_user.fio")
    @Mapping(target = "created_date", source = "created_date")
    public CourseRsDto toResponse(Course item) ;

    @Override
    public List<CourseRsDto> toResponseList(List<Course> items);
}
