package uz.firdavs.taskmanager.service;

 import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.req.ReqTechnologies;

public interface TechnologiesService {
    ResponseDto<?> getTechnologies();
    ResponseDto<?> getTechnologyById(Integer id);
    ResponseDto<?> getTechnologyByTechPartId(Integer techPartId);
    ResponseDto<?> createTechnology(ReqTechnologies reqName);
    ResponseDto<?> editTechnologyById(ReqTechnologies reqName, Integer id);
    ResponseDto<?> deleteTechnologyById(Integer id);
}
