package uz.firdavs.taskmanager.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.method.ParameterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Employees;
import uz.firdavs.taskmanager.entity.EmployeesTechnologies;
import uz.firdavs.taskmanager.projections.EmployeesProjection;
import uz.firdavs.taskmanager.repository.EmployeesRepository;
import uz.firdavs.taskmanager.repository.EmployeesTechnologiesRepository;
import uz.firdavs.taskmanager.req.ReqEmployees;
import uz.firdavs.taskmanager.service.EmployeesService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ImplEmployees implements EmployeesService {
    @Autowired
    EmployeesRepository repository;
    @Autowired
    EmployeesTechnologiesRepository employeesTechnologiesRepository;

    @Override
    public ResponseDto<?> getEmployees() {
        List<EmployeesProjection> all = repository.selectEmployees();
        if (all.size()>0){
            return new ResponseDto<>(true,"ok",all);
        }
        return new ResponseDto<>(false,"malumotlat topilmadi");    }

    @Override
    public ResponseDto<?> getEmployeeById(Integer id) {
        Optional<Employees> byId = repository.findById(id);
        if (byId.isPresent()){
            return new ResponseDto<>(true,"ok",byId);
        }
        return new ResponseDto<>(false,"byunday malumot topilmadi id:",id);
    }

    @Override
    public ResponseDto<?> createEmployee(ReqEmployees reqEmployees) {
        Employees emp = new Employees();
        emp.setName(reqEmployees.getName());
        emp.setDepartments_id(reqEmployees.getDepartments_id());

        try {
            Employees save = repository.save(emp);
            if (reqEmployees.getIdsList().size()>0){
                Integer empId = save.getId();
                List<EmployeesTechnologies> et = new ArrayList<>();

                for (Integer techId:reqEmployees.getIdsList()) {
                    EmployeesTechnologies empt = new EmployeesTechnologies();
                    empt.setEmp_id(empId);
                    empt.setTech_id(techId);
                    et.add(empt);
                }
                employeesTechnologiesRepository.saveAll(et);
            }

            return new ResponseDto<>(true,"yaratildi");
        } catch (Exception e){
            log.error("Malamotlar bilan ishlashda xatolik "+e.getMessage());
            return new ResponseDto<>(false,"Yaratishda xatolik");
        }
    }

    @Transactional
    @Override
    public ResponseDto<?> editEmployeeById(ReqEmployees reqEmployees, Integer id) {
        Optional<Employees> byId = repository.findById(id);
        if (byId.isPresent()){
            Employees departments = new Employees();
            departments.setName(reqEmployees.getName());
            departments.setDepartments_id(reqEmployees.getDepartments_id());
            departments.setId(id);
            try {
                Employees save = repository.save(departments);
                if (reqEmployees.getIdsList().size()>0){
                    Integer empId = save.getId();
                    employeesTechnologiesRepository.deleteByEmp_id(empId);
                    List<EmployeesTechnologies> et = new ArrayList<>();
                    for (Integer techId:reqEmployees.getIdsList()) {
                        EmployeesTechnologies empt = new EmployeesTechnologies();
                        empt.setEmp_id(empId);
                        empt.setTech_id(techId);
                        et.add(empt);
                    }
                    employeesTechnologiesRepository.saveAll(et);
                }
                return new ResponseDto<>(true,"Muvaffaqiyatli tahrirlandi id:"+id);
            } catch (Exception e){
                log.error("Tahrirlashda xatolik "+e.getMessage());
                return new ResponseDto<>(false,"Tahrirlashda hatolik id:"+id);
            }
        }
        return new ResponseDto<>(false,"Obyekt topilmadi id:"+id);
    }

    @Override
    public ResponseDto<?> deleteEmployeeById(Integer id) {
        Optional<Employees> byId = repository.findById(id);
        if (byId.isPresent()){
            repository.deleteById(id);
            return new ResponseDto<>(true,"Muvaffaqiyatli bajarildi");
        }
        return new ResponseDto<>(false,"Obyekt topilmadi");
    }
}
