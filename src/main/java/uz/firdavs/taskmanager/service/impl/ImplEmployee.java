package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Department;
import uz.firdavs.taskmanager.entity.Employee;
import uz.firdavs.taskmanager.entity.EmployeeTechnology;
import uz.firdavs.taskmanager.entity.Technology;
import uz.firdavs.taskmanager.mapper.EmployeeMapper;
import uz.firdavs.taskmanager.payload.rq.EmployeeRqDto;
import uz.firdavs.taskmanager.projections.EmployeesProjection;
import uz.firdavs.taskmanager.repository.DepartmentsRepository;
import uz.firdavs.taskmanager.repository.EmployeeRepository;
import uz.firdavs.taskmanager.repository.EmployeeTechnologyRepository;
import uz.firdavs.taskmanager.repository.TechnologyRepository;
import uz.firdavs.taskmanager.service.EmployeesService;
import uz.firdavs.taskmanager.specifications.EmployeeSpecification;
import uz.firdavs.taskmanager.utis.Utils;

import java.util.*;

/**
 * created by: Firdavsbek
 * Date:       23.03.2024
 * Time:       23:18
 * Project:    task-manager
 */


@Slf4j
@Service
@RequiredArgsConstructor
public class ImplEmployee implements EmployeesService {

    private final EmployeeRepository repository;
    private final TechnologyRepository  technologyRepository;
    private final DepartmentsRepository departmentsRepository;
    private final EmployeeMapper mapper;
    private final EmployeeTechnologyRepository employeeTechnologyRepository;

    @Override
    public ResponseDto<?> findAll(Map<String, Object> map) {
        Specification<Employee> specs = EmployeeSpecification.selectEmployee();
        Specification<Employee> combinedSpecs = Specification.where(specs);
        return Utils.generatePageable(repository, combinedSpecs, mapper, map);
    }

    @Override
    public ResponseDto<?> getRowById(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<?> deleteRowById(Integer id) {
        employeeTechnologyRepository.deleteByEmp_id(id);
        Optional<Employee> byId = repository.findById(id);
        if (byId.isPresent()) {
            repository.deleteById(id);
            return new ResponseDto<>(true, "Muvaffaqiyatli bajarildi");
        }
        return new ResponseDto<>(false, "Obyekt topilmadi");
    }


    @Override
    public ResponseDto<?> selectEmployees() {
        List<EmployeesProjection> employeesProjections = repository.selectEmployees();
        if (employeesProjections.size()>0){
            return new ResponseDto<>(true,"OK",employeesProjections);
        }
        return new ResponseDto<>(false,"no data",new ArrayList<>());
    }

    @Override
    public ResponseDto<?> createRow(EmployeeRqDto req) {
        Employee emp = new Employee();
        emp.setName(req.getName());
        Optional<Department> department = departmentsRepository.findById(req.getDepartments_id());
        if (!department.isPresent()){
            return new ResponseDto<>(false, "Data not found id:" + req.getDepartments_id());

        }
        emp.setDepartment(department.get());

        try {
            Employee saved = repository.save(emp);
            if (req.getIdsList().size() > 0) {
                List<EmployeeTechnology> et = new ArrayList<>();

                for (Integer techId : req.getIdsList()) {
                    Optional<Technology> technology = technologyRepository.findById(techId);
                    if (!technology.isPresent()){
                        return new ResponseDto<>(false, "Data not found id:" + techId);
                    }
                    EmployeeTechnology empt = new EmployeeTechnology();
                    empt.setEmployee(saved);
                    empt.setTechnology(technology.get());
                    et.add(empt);
                }
                employeeTechnologyRepository.saveAll(et);
            }

            return new ResponseDto<>(true, "yaratildi");
        } catch (Exception e) {
            log.error("Malamotlar bilan ishlashda xatolik " + e.getMessage());
            return new ResponseDto<>(false, "Yaratishda xatolik");
        }
    }

    @Override
    public ResponseDto<?> editRowById(EmployeeRqDto req, Integer id) {
        Optional<Employee> byId = repository.findById(id);
        if (byId.isPresent()){
            Employee employee = new Employee();
            employee.setName(req.getName());
            Optional<Department> department = departmentsRepository.findById(req.getDepartments_id());
            if (!department.isPresent()){
                return new ResponseDto<>(false,"data  not found id:"+req.getDepartments_id());
            }
            employee.setDepartment(department.get());
            employee.setId(id);
            repository.save(employee);
            try {
                if (req.getIdsList().size()>0){
                    Integer empId = byId.get().getId();
                    employeeTechnologyRepository.deleteByEmp_id(empId);
                    List<EmployeeTechnology> et = new LinkedList<>();
                    for (Integer techId:req.getIdsList()) {
                        EmployeeTechnology empt = new EmployeeTechnology();
                        empt.setEmployee(byId.get());
                        Optional<Technology> technology = technologyRepository.findById(techId);
                        if (!technology.isPresent()){
                            return new ResponseDto<>(false, "Data not found id:" + techId);
                        }
                        empt.setTechnology(technology.get());
                        et.add(empt);
                    }
                    employeeTechnologyRepository.saveAll(et);
                }
                return new ResponseDto<>(true,"Muvaffaqiyatli tahrirlandi id:"+id);
            } catch (Exception e){
                log.error("Tahrirlashda xatolik "+e.getMessage());
                return new ResponseDto<>(false,"Tahrirlashda hatolik id:"+id);
            }
        }
        return new ResponseDto<>(false,"Obyekt topilmadi id:"+id);
    }
}






