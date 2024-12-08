package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Course;
import uz.firdavs.taskmanager.mapper.CourseMapper;
import uz.firdavs.taskmanager.payload.rq.CourseRqDto;
import uz.firdavs.taskmanager.repository.CourseRepository;
import uz.firdavs.taskmanager.service.CourseService;
import uz.firdavs.taskmanager.specifications.CourseSpecification;
import uz.firdavs.taskmanager.utis.Utils;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImplCourse implements CourseService {
    private final CourseRepository repository;
    private final CourseMapper mapper;


    @Override
    public ResponseDto<?> createRow(CourseRqDto req) {
        Course entity = new Course();
        entity.setName(req.getName());
        entity.setPrice(req.getPrice());
        entity.setLink(req.getLink());
        entity.setLogin(req.getLogin());
        entity.setPassword(req.getPassword());
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
    public ResponseDto<?> editRowById(CourseRqDto req, Integer id) {
        Optional<Course> rowById = repository.findById(id);
        if (rowById.isPresent()) {
            Course entity = new Course();
            entity.setId(id);
            entity.setName(req.getName());
            entity.setPrice(req.getPrice());
            entity.setLink(req.getLink());
            entity.setLogin(req.getLogin());
            entity.setPassword(req.getPassword());
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
        Specification<Course> specs = CourseSpecification.filterTable(map);
        Specification<Course> combinedSpecs = Specification.where(specs);
        return Utils.generatePageable(repository, combinedSpecs, mapper, map);
    }

    @Override
    public ResponseDto<?> getRowById(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<?> deleteRowById(Integer id) {
        Optional<Course> byId = repository.findById(id);
        if (byId.isPresent()) {
            repository.deleteById(id);
            return new ResponseDto<>(true, "Muvaffaqiyatli o'chirildi ID:"+id);
        }
        return new ResponseDto<>(false, "Obyekt topilmadi ID:"+id);
    }
}
