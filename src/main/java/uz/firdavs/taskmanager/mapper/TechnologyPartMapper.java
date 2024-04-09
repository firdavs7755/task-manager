package uz.firdavs.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.firdavs.taskmanager.entity.TechnologyPart;
import uz.firdavs.taskmanager.mapper.base.BaseMapper;
import uz.firdavs.taskmanager.payload.rs.TechnologyPartRsDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface TechnologyPartMapper extends BaseMapper<TechnologyPartRsDto, TechnologyPart> {
    @Override
    public TechnologyPartRsDto toResponse(TechnologyPart director) ;

    @Override
    public List<TechnologyPartRsDto> toResponseList(List<TechnologyPart> directors);
}
