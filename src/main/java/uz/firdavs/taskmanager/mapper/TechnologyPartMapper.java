package uz.firdavs.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.firdavs.taskmanager.entity.TechnologyPart;
import uz.firdavs.taskmanager.mapper.base.BaseMapper;
import uz.firdavs.taskmanager.payload.rs.TechnologyPartRsDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface TechnologyPartMapper extends BaseMapper<TechnologyPartRsDto, TechnologyPart> {
    @Override
    @Mapping(target = "created_user_id", source = "created_user.id")
    @Mapping(target = "created_user_fio", source = "created_user.fio")
    public TechnologyPartRsDto toResponse(TechnologyPart item) ;

    @Override
    public List<TechnologyPartRsDto> toResponseList(List<TechnologyPart> items);
}
