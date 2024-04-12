package uz.firdavs.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.firdavs.taskmanager.entity.Director;
import uz.firdavs.taskmanager.mapper.base.BaseMapper;
import uz.firdavs.taskmanager.payload.rq.DirectorRqDto;
import uz.firdavs.taskmanager.payload.rs.DirectorRsDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface DirectorMapper extends BaseMapper<DirectorRsDto, Director> {

    Director toEntity(DirectorRqDto req);

    @Override
    @Mapping(target = "created_user_id", source = "created_user.id")
    @Mapping(target = "created_user_fio", source = "created_user.fio")
    public DirectorRsDto toResponse(Director director) ;

    @Override
    public List<DirectorRsDto> toResponseList(List<Director> directors);
}
