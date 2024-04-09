package uz.firdavs.taskmanager.mapper.base;

import java.util.List;

public interface BaseMapper <RESPONSE_DTO,ENTITY> extends Mapper {

    RESPONSE_DTO toResponse (ENTITY entity);

    List<RESPONSE_DTO> toResponseList(List<ENTITY> entities);

}
