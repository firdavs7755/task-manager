package uz.firdavs.taskmanager.service;

 import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.rq.TechnologyRqDto;
 import uz.firdavs.taskmanager.service.base.BaseService;

public interface TechnologiesService extends BaseService {
    ResponseDto<?> createRow(TechnologyRqDto req);
    ResponseDto<?> editRowById(TechnologyRqDto req, Integer id);
    /*ResponseDto<?> getTechnologyByTechPartId(Integer id);*/

}
