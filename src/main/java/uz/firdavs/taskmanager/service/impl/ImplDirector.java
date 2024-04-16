package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Director;
import uz.firdavs.taskmanager.mapper.DirectorMapper;
import uz.firdavs.taskmanager.payload.rq.DirectorRqDto;
import uz.firdavs.taskmanager.repository.DirectorRepository;
import uz.firdavs.taskmanager.service.DirectorService;
import uz.firdavs.taskmanager.specifications.DirectorSpecification;
import uz.firdavs.taskmanager.utis.Utils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImplDirector implements DirectorService {
    private final DirectorRepository repository;
    private final DirectorMapper mapper;


    @Override
    public ResponseDto<?> createRow(DirectorRqDto req) {
        Director entity = mapper.toEntity(req);
        entity.setCreated_user(Utils.getUser());
        try {
            repository.save(entity);
            return new ResponseDto<>(true,"yaratildi");
        } catch (Exception e){
            log.error("Malarial bilan ishlashda xatolik "+e.getMessage());
            return new ResponseDto<>(false,"Yaratishda xatolik");
        }
    }

    @Override
    public ResponseDto<?> editRowById(DirectorRqDto req, Integer id) {
        Optional<Director> rowById = repository.findById(id);
        if (rowById.isPresent()){
            Director entity = mapper.toEntity(req);
            entity.setId(id);
            entity.setCreated_user(rowById.get().getCreated_user());
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
        Specification<Director> specs = DirectorSpecification.filterTable(map);
        Specification<Director> combinedSpecs = Specification.where(specs);
        return Utils.generatePageable(repository, combinedSpecs, mapper, map);
    }

    @Override
    public ResponseDto<?> getRowById(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<?> deleteRowById(Integer id) {
        Optional<Director> byId = repository.findById(id);
        if (byId.isPresent()){
            repository.deleteById(id);
            return new ResponseDto<>(true,"Muvaffaqiyatli bajarildi");
        }
        return new ResponseDto<>(false,"Obyekt topilmadi");
    }
}
