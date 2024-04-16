package uz.firdavs.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.firdavs.taskmanager.entity.Top;
import uz.firdavs.taskmanager.mapper.base.BaseMapper;
import uz.firdavs.taskmanager.payload.rs.TopRsDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface TopMapper extends BaseMapper<TopRsDto, Top> {
    @Override
    @Mapping(target = "created_user_id", source = "created_user.id")
    @Mapping(target = "created_user_name", source = "created_user.fio")
    public TopRsDto toResponse(Top item) ;

    @Override
    public List<TopRsDto> toResponseList(List<Top> items);
}
