package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Employee;
import uz.firdavs.taskmanager.entity.Top;
import uz.firdavs.taskmanager.entity.TopLike;
import uz.firdavs.taskmanager.mapper.TopMapper;
import uz.firdavs.taskmanager.payload.rq.TopLikeRqDto;
import uz.firdavs.taskmanager.repository.EmployeeRepository;
import uz.firdavs.taskmanager.repository.TopLikeRepository;
import uz.firdavs.taskmanager.repository.TopRepository;
import uz.firdavs.taskmanager.service.TopLikeService;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImplTopLike implements TopLikeService {
    private final TopLikeRepository repository;
    private final TopRepository topRepository;
    private final EmployeeRepository employeeRepository;
    private final TopMapper mapper;


    @Override
    public ResponseDto<?> createRow(TopLikeRqDto req) {
        Optional<Top> top = topRepository.findById(req.getTop_id());
        if (!top.isPresent()){
            return new ResponseDto<>(false, "Top not found id:"+req.getTop_id());
        }
        Optional<Employee> employee = employeeRepository.findById(req.getEmployee_id());
        if (!employee.isPresent()){
            return new ResponseDto<>(false, "Employee not found id:"+req.getEmployee_id());
        }
        Optional<TopLike> topLike = repository.findByEmployeeAndTop(employee.get(),top.get());

        try {
            if (topLike.isPresent()){
                repository.deleteByEmp_id(req.getEmployee_id(), req.getTop_id());
                return new ResponseDto<>(true, "ok");
            } else {
                TopLike entity = new TopLike();
                entity.setTop(top.get());
                entity.setEmployee(employee.get());
                repository.save(entity);
                return new ResponseDto<>(true, "Muvaffaqiyatli yaratildi");
            }
        } catch (Exception e) {
            log.error("Ma'lumotlar bilan ishlashda xatolik " + e.getMessage());
            return new ResponseDto<>(false, "Yaratishda xatolik");
        }
    }

    @Override
    public ResponseDto<?> editRowById(TopLikeRqDto req, Integer id) {
        return null;
    }

    @Override
    public ResponseDto<?> findAll(Map<String, Object> map) {
        return null;
    }

    @Override
    public ResponseDto<?> getRowById(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<?> deleteRowById(Integer id) {
        return null;
    }


}
