package uz.firdavs.taskmanager.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Technology_part;
import uz.firdavs.taskmanager.projections.ReportProjection;
import uz.firdavs.taskmanager.repository.Technology_partRepository;
import uz.firdavs.taskmanager.req.ReqName;
import uz.firdavs.taskmanager.service.Technology_partService;
import uz.firdavs.taskmanager.service.Technology_partService;
import uz.firdavs.taskmanager.specifications.TechnologyPartSpecefication;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ImplTechnology_part implements Technology_partService {
    @Autowired
    Technology_partRepository repository;
    @Override
    public ResponseDto<?> getTechnology_part() {
        List<Technology_part > all = repository.selectTechnologyPart();
        if (all.size()>0){
            return new ResponseDto<>(true,"ok",all);
        }
        return new ResponseDto<>(false,"malumotlat topilmadi");
    }

    @Override
    public ResponseDto<?> selectEmpsSectionByTechPart() {
        List<ReportProjection> reportProjections = repository.selectEmpsSectionByTechPart();
        if (!reportProjections.isEmpty()){
            return new ResponseDto<>(true,"ok",reportProjections);
        }
        return new ResponseDto<>(true,"ops!",reportProjections);
    }

    @Override
    public ResponseDto<?> getTechnology_partFilter(Integer id,String name) {
        Specification<Technology_part> spec = Specification.where(null);
        if (id!=null){
            spec = spec.and(TechnologyPartSpecefication.withId(id));
        }
        if (name!=null){
            spec = spec.and(TechnologyPartSpecefication.withName(name));
        }
        return new ResponseDto<>(true,"ok",repository.findAll(spec));

    }

    @Override
    public ResponseDto<?> getTechnology_partById(Integer id) {
        Optional<Technology_part> byId = repository.findById(id);
        if (byId.isPresent()){
            return new ResponseDto<>(true,"ok",byId);
        }
        return new ResponseDto<>(false,"byunday malumot topilmadi id:",id);
    }

    @Override
    public ResponseDto<?> createTechnology_part(ReqName reqName) {
        Technology_part technologies = new Technology_part();
        technologies.setName(reqName.getName());
        try {
            repository.save(technologies);
            return new ResponseDto<>(true,"yaratildi");
        } catch (Exception e){
            log.error("Malarial bilan ishlashda xatolik "+e.getMessage());
            return new ResponseDto<>(false,"Yaratishda xatolik");
        }
    }

    @Override
    public ResponseDto<?> editTechnology_partById(ReqName reqName, Integer id) {
        Optional<Technology_part> byId = repository.findById(id);
        if (byId.isPresent()){
            Technology_part technologies = new Technology_part();
            technologies.setName(reqName.getName());
            technologies.setId(id);
            try {
                repository.save(technologies);
                return new ResponseDto<>(true,"Muvaffaqiyatli tahrirlandi id:"+id);
            } catch (Exception e){
                log.error("Tahrirlashda xatolik "+e.getMessage());
                return new ResponseDto<>(false,"Tahrirlashda hatolik id:"+id);
            }
        }
        return new ResponseDto<>(false,"Obyekt topilmadi id:"+id);
    }

    @Override
    public ResponseDto<?> deleteTechnology_partById(Integer id) {
        Optional<Technology_part> byId = repository.findById(id);
        if (byId.isPresent()){
            repository.deleteById(id);
            return new ResponseDto<>(true,"Muvaffaqiyatli bajarildi");
        }
        return new ResponseDto<>(false,"Obyekt topilmadi");
    }
}
