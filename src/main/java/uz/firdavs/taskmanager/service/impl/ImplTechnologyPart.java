package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.TechnologyPart;
import uz.firdavs.taskmanager.mapper.TechnologyPartMapper;
import uz.firdavs.taskmanager.payload.rq.TechnologyPartRqDto;
import uz.firdavs.taskmanager.projections.ReportProjection;
import uz.firdavs.taskmanager.repository.TechnologyPartRepository;
import uz.firdavs.taskmanager.service.TechnologyPartService;
import uz.firdavs.taskmanager.specifications.TechnologyPartSpecification;
import uz.firdavs.taskmanager.utis.Utils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImplTechnologyPart implements TechnologyPartService {
    private final TechnologyPartRepository repository;
    private final TechnologyPartMapper mapper;


    @Override
    public ResponseDto<?> selectEmpsSectionByTechPart() {
        List<ReportProjection> reportProjections = repository.selectEmpsSectionByTechPart();
        if (!reportProjections.isEmpty()){
            return new ResponseDto<>(true,"ok",reportProjections);
        }
        return new ResponseDto<>(true,"ops!",reportProjections);
    }




    @Override
    public ResponseDto<?> createRow(TechnologyPartRqDto req) {
        TechnologyPart technologies = new TechnologyPart();
        technologies.setName(req.getName());
        technologies.setCreated_user(Utils.getUser());
        try {
            repository.save(technologies);
            return new ResponseDto<>(true,"yaratildi");
        } catch (Exception e){
            log.error("Malarial bilan ishlashda xatolik "+e.getMessage());
            return new ResponseDto<>(false,"Yaratishda xatolik");
        }
    }

    @Override
    public ResponseDto<?> editRowById(TechnologyPartRqDto req, Integer id) {
        Optional<TechnologyPart> rowById = repository.findById(id);
        if (rowById.isPresent()){
            TechnologyPart entity = new TechnologyPart();
            entity.setName(req.getName());
            entity.setCreated_user(rowById.get().getCreated_user());
            entity.setId(id);
            try {
                repository.save(entity);
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
        Specification<TechnologyPart> specs = TechnologyPartSpecification.filterTable(map);
        Specification<TechnologyPart> combinedSpecs = Specification.where(specs);
        return Utils.generatePageable(repository, combinedSpecs, mapper, map);
    }

    @Override
    public ResponseDto<?> getRowById(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<?> deleteRowById(Integer id) {
        Optional<TechnologyPart> byId = repository.findById(id);
        if (byId.isPresent()){
            repository.deleteById(id);
            return new ResponseDto<>(true,"Muvaffaqiyatli bajarildi");
        }
        return new ResponseDto<>(false,"Obyekt topilmadi");
    }
}
