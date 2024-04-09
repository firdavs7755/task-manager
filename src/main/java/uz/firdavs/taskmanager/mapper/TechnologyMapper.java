package uz.firdavs.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.firdavs.taskmanager.entity.Technology;
import uz.firdavs.taskmanager.mapper.base.BaseMapper;
import uz.firdavs.taskmanager.payload.rs.TechnologyRsDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface TechnologyMapper extends BaseMapper<TechnologyRsDto, Technology> {
    @Override
    @Mapping(target = "technology_part_id", source = "technologyPart.id")
    @Mapping(target = "technology_part_name", source = "technologyPart.name")
    public TechnologyRsDto toResponse(Technology technology) ;

    @Override
    public List<TechnologyRsDto> toResponseList(List<Technology> technologies);
}
