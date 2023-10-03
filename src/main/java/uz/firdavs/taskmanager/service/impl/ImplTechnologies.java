package uz.firdavs.taskmanager.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Technologies;
import uz.firdavs.taskmanager.projections.TechnologiesProjection;
import uz.firdavs.taskmanager.repository.TechnologiesRepository;
import uz.firdavs.taskmanager.req.ReqTechnologies;
import uz.firdavs.taskmanager.service.TechnologiesService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ImplTechnologies implements TechnologiesService {
    @Autowired
    TechnologiesRepository repository;
    @Override
    public ResponseDto<?> getTechnologies() {
//        List<Technologies> all = repository.findAll();
//        if (all.size()>0){
//            return new ResponseDto<>(true,"ok",all);
//        }
        List<TechnologiesProjection> selectTechnologies = repository.selectTechnologies();
        if (selectTechnologies.size()>0){
            return new ResponseDto<>(true,"ok",selectTechnologies);
        }
        return new ResponseDto<>(false,"malumotlat topilmadi");
    }

    @Override
    public ResponseDto<?> getTechnologyById(Integer id) {
        Optional<Technologies> byId = repository.findById(id);
        if (byId.isPresent()){
            return new ResponseDto<>(true,"ok",byId);
        }
        return new ResponseDto<>(false,"byunday malumot topilmadi id:",id);
    }

    @Override
    public ResponseDto<?> getTechnologyByTechPartId(Integer techPartId) {
        if (techPartId==100){//todo fullStack
            List<Technologies> all = repository.findAll();
            return new ResponseDto<>(true,"ok",all);
        }else {
            List<Technologies> allByTechnology_part_id = repository.selectTechnologiesByTechPartId(techPartId);
            return new ResponseDto<>(true,"ok",allByTechnology_part_id);
        }
    }


    @Override
    public ResponseDto<?> createTechnology(ReqTechnologies reqTechnologies) {
        Technologies technologies = new Technologies();
        technologies.setName(reqTechnologies.getName().toUpperCase());
        technologies.setTechnology_part_id(reqTechnologies.getTechnology_part_id());
        try {
            int i = repository.countByName(reqTechnologies.getName().toUpperCase());
            if (i>0){
                return new ResponseDto<>(false,"Texnologiya mavjud");
            }
            repository.save(technologies);
            return new ResponseDto<>(true,"yaratildi");
        } catch (Exception e){
            log.error("Malarial bilan ishlashda xatolik "+e.getMessage());
            return new ResponseDto<>(false,"Yaratishda xatolik");
        }
    }



    @Override
    public ResponseDto<?> editTechnologyById(ReqTechnologies reqTechnologies, Integer id) {
        Optional<Technologies> byId = repository.findById(id);
        if (byId.isPresent()){
            Technologies technologies = new Technologies();
            technologies.setName(reqTechnologies.getName());
            technologies.setTechnology_part_id(reqTechnologies.getTechnology_part_id());
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
    public ResponseDto<?> deleteTechnologyById(Integer id) {
        Optional<Technologies> byId = repository.findById(id);
        if (byId.isPresent()){
            repository.deleteById(id);
            return new ResponseDto<>(true,"Muvaffaqiyatli bajarildi");
        }
        return new ResponseDto<>(false,"Obyekt topilmadi");
    }
}
