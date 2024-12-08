package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Project;
import uz.firdavs.taskmanager.mapper.ProjectMapper;
import uz.firdavs.taskmanager.payload.rq.NameRq;
import uz.firdavs.taskmanager.repository.ProjectRepository;
import uz.firdavs.taskmanager.service.ProjectService;
import uz.firdavs.taskmanager.specifications.ProjectSpecification;
import uz.firdavs.taskmanager.utis.Utils;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImplProject implements ProjectService {
    private final ProjectRepository repository;
    private final ProjectMapper mapper;


    @Override
    public ResponseDto<?> createRow(NameRq req) {
        Project entity = new Project();
        entity.setName(req.getName());
        entity.setCreated_user(Utils.getUser());
        try {
            repository.save(entity);
            return new ResponseDto<>(true, "Muvaffaqiyatli yaratildi");
        } catch (Exception e) {
            log.error("Ma'lumotlar bilan ishlashda xatolik " + e.getMessage());
            return new ResponseDto<>(false, "Yaratishda xatolik");
        }
    }

    @Override
    public ResponseDto<?> editRowById(NameRq req, Integer id) {
        Optional<Project> rowById = repository.findById(id);
        if (rowById.isPresent()) {
            Project entity = new Project();
            entity.setId(id);
            entity.setName(req.getName());
            entity.setCreated_user(rowById.get().getCreated_user());
            try {
                repository.save(entity);
                return new ResponseDto<>(true, "Muvaffaqiyatli tahrirlandi id:" + id);
            } catch (Exception e) {
                log.error("Tahrirlashda xatolik " + e.getMessage());
                return new ResponseDto<>(false, "Tahrirlashda hatolik id:" + id);
            }
        }
        return new ResponseDto<>(false, "Obyekt topilmadi id:" + id);
    }

    @Override
    public ResponseDto<?> findAll(Map<String, Object> map) {
        Specification<Project> specs = ProjectSpecification.filterTable(map);
        Specification<Project> combinedSpecs = Specification.where(specs);
        return Utils.generatePageable(repository, combinedSpecs, mapper, map);
    }

    @Override
    public ResponseDto<?> getRowById(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<?> deleteRowById(Integer id) {
        Optional<Project> byId = repository.findById(id);
        if (byId.isPresent()) {
            repository.deleteById(id);
            return new ResponseDto<>(true, "Muvaffaqiyatli o'chirildi ID:"+id);
        }
        return new ResponseDto<>(false, "Obyekt topilmadi ID:"+id);
    }
}
