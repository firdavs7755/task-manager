package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Course;
import uz.firdavs.taskmanager.entity.NewCourseApplication;
import uz.firdavs.taskmanager.mapper.NewCourseApplicationMapper;
import uz.firdavs.taskmanager.payload.rq.NewCourseApplicationRqDto;
import uz.firdavs.taskmanager.repository.NewCourseApplicationRepository;
import uz.firdavs.taskmanager.service.NewCourseApplicationService;
import uz.firdavs.taskmanager.specifications.CourseSpecification;
import uz.firdavs.taskmanager.specifications.NewCourseApplicationSpecification;
import uz.firdavs.taskmanager.utis.Utils;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImplNewCourseApplication implements NewCourseApplicationService {
    private final NewCourseApplicationRepository repository;
    private final NewCourseApplicationMapper mapper;


    @Override
    public ResponseDto<?> createRow(NewCourseApplicationRqDto req) {
        NewCourseApplication entity = new NewCourseApplication();
        entity.setName(req.getName());
        entity.setPrice(req.getPrice());
        entity.setLink(req.getLink());
        entity.setDescription(req.getDescription());
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
    public ResponseDto<?> editRowById(NewCourseApplicationRqDto req, Integer id) {
        Optional<NewCourseApplication> rowById = repository.findById(id);
        if (rowById.isPresent()) {
            NewCourseApplication entity = new NewCourseApplication();
            entity.setId(id);
            entity.setName(req.getName());
            entity.setPrice(req.getPrice());
            entity.setLink(req.getLink());
            entity.setDescription(req.getDescription());
            entity.setCreated_date(rowById.get().getCreated_date());
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
        Specification<NewCourseApplication> specs = NewCourseApplicationSpecification.filterTable(map);
        Specification<NewCourseApplication> combinedSpecs = Specification.where(specs);
        return Utils.generatePageable(repository, combinedSpecs, mapper, map);
    }

    @Override
    public ResponseDto<?> getRowById(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<?> deleteRowById(Integer id) {
        Optional<NewCourseApplication> byId = repository.findById(id);
        if (byId.isPresent()) {
//            repository.deleteById(id);
            return new ResponseDto<>(true, "Muvaffaqiyatli o'chirildi ID:"+id);
        }
        return new ResponseDto<>(false, "Obyekt topilmadi ID:"+id);
    }
}
