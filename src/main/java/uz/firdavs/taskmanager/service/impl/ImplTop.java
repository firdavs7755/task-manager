package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Top;
import uz.firdavs.taskmanager.mapper.TopMapper;
import uz.firdavs.taskmanager.mapper.TopMapper;
import uz.firdavs.taskmanager.payload.rq.TopRqDto;
import uz.firdavs.taskmanager.projections.ReportProjection;
import uz.firdavs.taskmanager.repository.TopRepository;
import uz.firdavs.taskmanager.repository.TopRepository;
import uz.firdavs.taskmanager.service.TopService;
import uz.firdavs.taskmanager.service.TopService;
import uz.firdavs.taskmanager.specifications.TopSpecification;
import uz.firdavs.taskmanager.utis.Utils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImplTop implements TopService {
    private final TopRepository repository;
    private final TopMapper mapper;


    @Override
    public ResponseDto<?> selectTopLike() {
        return new ResponseDto<>(true,"Ok",repository.selectTopLike());
    }

    @Override
    public ResponseDto<?> createRow(TopRqDto req) {
        Top entity = new Top();
        entity.setName(req.getName());
        entity.setDescription(req.getDescription());
        entity.setCreated_user(Utils.getUser());
        try {
            repository.save(entity);
            return new ResponseDto<>(true, "yaratildi");
        } catch (Exception e) {
            log.error("Ma'lumotlar bilan ishlashda xatolik " + e.getMessage());
            return new ResponseDto<>(false, "Yaratishda xatolik");
        }
    }

    @Override
    public ResponseDto<?> editRowById(TopRqDto req, Integer id) {
        Optional<Top> rowById = repository.findById(id);
        if (rowById.isPresent()) {
            Top entity = new Top();
            entity.setId(id);
            entity.setName(req.getName());
            entity.setDescription(req.getDescription());
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
        Specification<Top> specs = TopSpecification.filterTable(map);
        Specification<Top> combinedSpecs = Specification.where(specs);
        return Utils.generatePageable(repository, combinedSpecs, mapper, map);
    }

    @Override
    public ResponseDto<?> getRowById(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<?> deleteRowById(Integer id) {
        Optional<Top> byId = repository.findById(id);
        if (byId.isPresent()) {
            repository.deleteById(id);
            return new ResponseDto<>(true, "Muvaffaqiyatli bajarildi");
        }
        return new ResponseDto<>(false, "Obyekt topilmadi");
    }
}
