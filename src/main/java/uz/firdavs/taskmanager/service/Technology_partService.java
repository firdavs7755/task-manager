package uz.firdavs.taskmanager.service;

 import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.req.ReqName;

public interface Technology_partService {
    ResponseDto<?> getTechnology_part();
    ResponseDto<?> selectEmpsSectionByTechPart();
    ResponseDto<?> getTechnology_partFilter(Integer id,String name);
    ResponseDto<?> getTechnology_partById(Integer id);
    ResponseDto<?> createTechnology_part(ReqName reqName);
    ResponseDto<?> editTechnology_partById(ReqName reqName, Integer id);
    ResponseDto<?> deleteTechnology_partById(Integer id);
}
