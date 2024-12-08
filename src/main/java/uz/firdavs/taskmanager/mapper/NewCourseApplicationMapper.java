package uz.firdavs.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.firdavs.taskmanager.entity.NewCourseApplication;
import uz.firdavs.taskmanager.mapper.base.BaseMapper;
import uz.firdavs.taskmanager.payload.rs.CourseRsDto;
import uz.firdavs.taskmanager.payload.rs.NewCourseApplicationRsDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface NewCourseApplicationMapper extends BaseMapper<NewCourseApplicationRsDto, NewCourseApplication> {
    @Override
    @Mapping(target = "created_user_id", source = "created_user.id")
    @Mapping(target = "created_user_fio", source = "created_user.fio")
    @Mapping(target = "created_date", source = "created_date")
    public NewCourseApplicationRsDto toResponse(NewCourseApplication item) ;

    @Override
    public List<NewCourseApplicationRsDto> toResponseList(List<NewCourseApplication> items);
}
