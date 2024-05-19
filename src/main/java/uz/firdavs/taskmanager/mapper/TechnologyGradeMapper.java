package uz.firdavs.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.firdavs.taskmanager.entity.TechnologyGrade;
import uz.firdavs.taskmanager.mapper.base.BaseMapper;
import uz.firdavs.taskmanager.payload.rs.base.BaseName;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface TechnologyGradeMapper extends BaseMapper<BaseName, TechnologyGrade> {

    @Override
    public BaseName toResponse(TechnologyGrade item) ;

    @Override
    public List<BaseName> toResponseList(List<TechnologyGrade> items);
}
