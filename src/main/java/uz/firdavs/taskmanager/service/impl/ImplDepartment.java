package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Department;
import uz.firdavs.taskmanager.entity.Director;
import uz.firdavs.taskmanager.mapper.DepartmentMapper;
import uz.firdavs.taskmanager.payload.rq.DepartmentRqDto;
import uz.firdavs.taskmanager.projections.ReportProjection;
import uz.firdavs.taskmanager.repository.DepartmentsRepository;
import uz.firdavs.taskmanager.payload.rq.NameRq;
import uz.firdavs.taskmanager.repository.DirectorRepository;
import uz.firdavs.taskmanager.service.DepartmentsService;
import uz.firdavs.taskmanager.specifications.DepartmentSpecification;
import uz.firdavs.taskmanager.utis.Utils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImplDepartment implements DepartmentsService {

    private final DepartmentsRepository repository;
    private final DirectorRepository directorRepository;
    private final DepartmentMapper mapper;

    @Override
    public ResponseDto<?> findAll(Map<String, Object> map) {
        Specification<Department> specs = DepartmentSpecification.filterTable(map);
        Specification<Department> combinedSpecs = Specification.where(specs);
        return Utils.generatePageable(repository, combinedSpecs, mapper, map);
    }

    @Override
    public ResponseDto<?> getRowById(Integer id) {
        return null;
    }



    @Override
    public ResponseDto<?> selectEmpsSectionByDepar() {
        List<ReportProjection> reportProjections = repository.selectEmpsSectionByDepar();
        if (!reportProjections.isEmpty()) {
            return new ResponseDto<>(true, "ok", reportProjections);
        }
        return new ResponseDto<>(true, "ok", reportProjections);

    }

    @Override
    public ResponseDto<?> createRow(DepartmentRqDto req) {
        Department entity = new Department();
        entity.setName(req.getName());
        if (req.getDirector_id()!=null){
            Optional<Director> director = directorRepository.findById(req.getDirector_id());
            director.ifPresent(entity::setDirector);
        }
        try {
            repository.save(entity);
            return new ResponseDto<>(true, "Success created ");
        } catch (Exception e) {
            log.error("Malamotlar bilan ishlashda xatolik " + e.getMessage());
            return new ResponseDto<>(false, "Yaratishda xatolik");

        }
    }

    @Override
    public ResponseDto<?> editRowById(DepartmentRqDto req, Integer id) {
        Optional<Department> rowById = repository.findById(id);
        if (!rowById.isPresent()){
            return new ResponseDto<>(false, "Data not found row id:" + id);
        }
        Optional<Director> director = directorRepository.findById(req.getDirector_id());
        if (!director.isPresent()) {
            return new ResponseDto<>(false, "Data not found id:" + req.getDirector_id());
        }
        Department entity = new Department();
        entity.setId(id);
        entity.setName(req.getName());
        entity.setDirector(director.get());
        try {
            repository.save(entity);
            return new ResponseDto<>(true, "Success created ");
        } catch (Exception e) {
            log.error("Malamotlar bilan ishlashda xatolik " + e.getMessage());
            return new ResponseDto<>(false, "Yaratishda xatolik");

        }
    }

    @Override
    public ResponseDto<?> deleteRowById(Integer id) {
        Optional<Department> rowById = repository.findById(id);
        if (!rowById.isPresent()){
            return new ResponseDto<>(false, "Data not found row id:" + id);
        }
        try {
            repository.deleteById(id);
            return new ResponseDto<>(true, "Success deleted ");
        } catch (Exception e) {
            log.error("Malamotlar bilan ishlashda xatolik " + e.getMessage());
            return new ResponseDto<>(false, "Malamotlar bilan ishlashda xatolik ");
        }
    }

}
