package uz.firdavs.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.firdavs.taskmanager.entity.Wish;
import uz.firdavs.taskmanager.mapper.base.BaseMapper;
import uz.firdavs.taskmanager.payload.rs.base.BaseName;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface WishMapper extends BaseMapper<BaseName, Wish> {

    @Override
    public BaseName toResponse(Wish item) ;

    @Override
    public List<BaseName> toResponseList(List<Wish> items);
}
