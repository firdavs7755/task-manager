package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Technology;
import uz.firdavs.taskmanager.entity.TechnologyPart;
import uz.firdavs.taskmanager.mapper.TechnologyMapper;
import uz.firdavs.taskmanager.repository.TechnologyPartRepository;
import uz.firdavs.taskmanager.repository.TechnologyRepository;
import uz.firdavs.taskmanager.payload.rq.TechnologyRqDto;
import uz.firdavs.taskmanager.service.TechnologiesService;
import uz.firdavs.taskmanager.specifications.TechnologySpecification;
import uz.firdavs.taskmanager.utis.Utils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImplTechnologies implements TechnologiesService {

    private final TechnologyRepository repository;
    private final TechnologyPartRepository technologyPartRepository;
    private final TechnologyMapper mapper;


    @Override
    public ResponseDto<?> getRowById(Integer id) {
        Optional<Technology> byId = repository.findById(id);
        if (byId.isPresent()){
            return new ResponseDto<>(true,"ok",byId);
        }
        return new ResponseDto<>(false,"byunday malumot topilmadi id:"+id);
    }

/*    @Override
    public ResponseDto<?> getTechnologyByTechPartId(Integer techPartId) {
        if (techPartId==100){//todo fullStack
            List<Technology> all = repository.findAll();
            return new ResponseDto<>(true,"ok",all);
        }else {
            List<Technology> allByTechnology_part_id = repository.selectTechnologiesByTechPartId(techPartId);
            return new ResponseDto<>(true,"ok",allByTechnology_part_id);
        }
    }*/


    @Override
    public ResponseDto<?> createRow(TechnologyRqDto req) {
        Technology technology = new Technology();
        technology.setName(req.getName());
        technology.setCreated_user(Utils.getUser());
        Optional<TechnologyPart> technologyPart = technologyPartRepository.findById(req.getTechnology_part_id());
        if (!technologyPart.isPresent()){
            return new ResponseDto<>(false,"Obyekt topilmadi");
        }
        technology.setTechnologyPart(technologyPart.get());
        try {
            int i = repository.countByName(req.getName());
            if (i>0){
                return new ResponseDto<>(false,"Texnologiya mavjud");
            }
            repository.save(technology);
            return new ResponseDto<>(true,"yaratildi");
        } catch (Exception e){
            log.error("Malarial bilan ishlashda xatolik "+e.getMessage());
            return new ResponseDto<>(false,"Yaratishda xatolik");
        }
    }



    @Override
    public ResponseDto<?> editRowById(TechnologyRqDto req, Integer id) {
        Optional<Technology> byId = repository.findById(id);
        if (byId.isPresent()){
            Technology technology = new Technology();
            technology.setName(req.getName());
            Optional<TechnologyPart> technologyPart = technologyPartRepository.findById(req.getTechnology_part_id());
            if (!technologyPart.isPresent()){
                return new ResponseDto<>(false,"Obyekt topilmadi");
            }
            technology.setTechnologyPart(technologyPart.get());
            technology.setId(id);
            try {
                repository.save(technology);
                return new ResponseDto<>(true,"Muvaffaqiyatli tahrirlandi id:"+id);
            } catch (Exception e){
                log.error("Tahrirlashda xatolik "+e.getMessage());
                return new ResponseDto<>(false,"Tahrirlashda hatolik id:"+id);
            }
        }
        return new ResponseDto<>(false,"Obyekt topilmadi id:"+id);
    }



    @Override
    public ResponseDto<?> findAll(Map<String, Object> map) {
        Specification<Technology> specs = TechnologySpecification.filterTable(map);
        Specification<Technology> combinedSpecs = Specification.where(specs);
        return Utils.generatePageable(repository, combinedSpecs, mapper, map);
    }


    @Override
    public ResponseDto<?> deleteRowById(Integer id) {
        Optional<Technology> byId = repository.findById(id);
        if (byId.isPresent()){
            repository.deleteById(id);
            return new ResponseDto<>(true,"Muvaffaqiyatli bajarildi");
        }
        return new ResponseDto<>(false,"Obyekt topilmadi");
    }
}
