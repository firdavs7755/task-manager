package uz.firdavs.taskmanager.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Departments;
import uz.firdavs.taskmanager.projections.ReportProjection;
import uz.firdavs.taskmanager.repository.DepartmentsRepository;
import uz.firdavs.taskmanager.req.ReqName;
import uz.firdavs.taskmanager.service.DepartmentsService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ImplDepartment implements DepartmentsService {
    @Autowired
    DepartmentsRepository repository;

    @Override
    public ResponseDto<?> getDepartments() {
        List<Departments> all = repository.findAll();
        if (all.size()>0){
            return new ResponseDto<>(true,"ok",all);
        }
        return new ResponseDto<>(false,"malumotlat topilmadi");    }

    @Override
    public ResponseDto<?> getDepartmentById(Integer id) {
        Optional<Departments> byId = repository.findById(id);
        if (byId.isPresent()){
            return new ResponseDto<>(true,"ok",byId);
        }
        return new ResponseDto<>(false,"byunday malumot topilmadi id:",id);
    }

    @Override
    public ResponseDto<?> selectEmpsSectionByDepar() {
        List<ReportProjection> reportProjections = repository.selectEmpsSectionByDepar();
        if (!reportProjections.isEmpty()){
            return new ResponseDto<>(true,"ok",reportProjections);
        }
        return new ResponseDto<>(true,"ok",reportProjections);

    }

    @Override
    public ResponseDto<?> createDepartment(ReqName reqName) {
        Departments departments = new Departments();
        departments.setName(reqName.getName());
        try {
            repository.save(departments);
            return new ResponseDto<>(true,"yaratildi");
        } catch (Exception e){
            log.error("Malamotlar bilan ishlashda xatolik "+e.getMessage());
            return new ResponseDto<>(false,"Yaratishda xatolik");
        }
    }

    @Override
    public ResponseDto<?> editDepartmentById(ReqName reqName, Integer id) {
        Optional<Departments> byId = repository.findById(id);
        if (byId.isPresent()){
            Departments departments = new Departments();
            departments.setName(reqName.getName());
            departments.setId(id);
            try {
                repository.save(departments);
                return new ResponseDto<>(true,"Muvaffaqiyatli tahrirlandi id:"+id);
            } catch (Exception e){
                log.error("Tahrirlashda xatolik "+e.getMessage());
                return new ResponseDto<>(false,"Tahrirlashda hatolik id:"+id);
            }
        }
        return new ResponseDto<>(false,"Obyekt topilmadi id:"+id);
    }

    @Override
    public ResponseDto<?> deleteDepartmentById(Integer id) {
        Optional<Departments> byId = repository.findById(id);
        if (byId.isPresent()){
            repository.deleteById(id);
            return new ResponseDto<>(true,"Muvaffaqiyatli bajarildi");
        }
        return new ResponseDto<>(false,"Obyekt topilmadi");
    }
}
