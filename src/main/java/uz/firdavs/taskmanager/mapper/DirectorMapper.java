package uz.firdavs.taskmanager.mapper;

import org.mapstruct.Mapper;
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
    public DirectorRsDto toResponse(Director director) ;

    @Override
    public List<DirectorRsDto> toResponseList(List<Director> directors);
}
