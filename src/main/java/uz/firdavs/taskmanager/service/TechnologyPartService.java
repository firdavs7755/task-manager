package uz.firdavs.taskmanager.service;

 import uz.firdavs.taskmanager.dto.ResponseDto;
 import uz.firdavs.taskmanager.payload.rq.TechnologyPartRqDto;
 import uz.firdavs.taskmanager.service.base.BaseService;

public interface TechnologyPartService extends BaseService {
    ResponseDto<?> selectEmpsSectionByTechPart();
    ResponseDto<?> createRow(TechnologyPartRqDto req);
    ResponseDto<?> editRowById(TechnologyPartRqDto req, Integer id);
}
