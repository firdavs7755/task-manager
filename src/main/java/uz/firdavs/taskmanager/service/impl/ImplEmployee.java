package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.*;
import uz.firdavs.taskmanager.mapper.EmployeeMapper;
import uz.firdavs.taskmanager.payload.rq.EmployeeRqDto;
import uz.firdavs.taskmanager.payload.rq.TechGrade;
import uz.firdavs.taskmanager.payload.rq.TechGradeRqDto;
import uz.firdavs.taskmanager.projections.EmployeesProjection;
import uz.firdavs.taskmanager.repository.*;
import uz.firdavs.taskmanager.service.EmployeesService;
import uz.firdavs.taskmanager.specifications.EmployeeSpecification;
import uz.firdavs.taskmanager.utis.Utils;

import java.awt.*;
import java.util.*;
import java.util.List;

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
    private final TechnologyRepository technologyRepository;
    private final ProjectRepository projectRepository;
    private final DepartmentsRepository departmentsRepository;
    private final WishRepository wishRepository;
    private final UsersRepository usersRepository;
    private final EmployeeMapper mapper;
    private final EmployeeTechnologyRepository employeeTechnologyRepository;
    private final TechnologyGradeRepository gradeRepository;
    private final EmployeeProjectRepository employeeProjectRepository;

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
    public ResponseDto<?> selectEmployees(Map<String, Object> map) {
        System.out.println("MAP:" + map);
        List<EmployeesProjection> employeesProjections = new ArrayList<>();
        String empName = "";
        String technologyName = "";
        String technology_part_id = "";
        String grade_id = "";
        String employee_type_id = "";
        String wish_id = "";
        String id_techs = null;
        String id_projects = null;
        if (map.get("empName") != null) {
            empName = Utils.checkStringValIsNull((String) map.get("empName"));
        }
        if (map.get("technologyName") != null) {
            technologyName = Utils.checkStringValIsNull((String) map.get("technologyName"));
        }
        if (map.get("technology_part_id") != null) {
            technology_part_id = Utils.checkIntValIsNull(Integer.parseInt((String) map.get("technology_part_id")));
        }
        if (map.get("grade_id") != null) {
            grade_id = Utils.checkIntValIsNull(Integer.parseInt((String) map.get("grade_id")));
        }
        if (map.get("employee_type_id") != null) {
            employee_type_id = Utils.checkIntValIsNull(Integer.parseInt((String) map.get("employee_type_id")));
        }
        if (map.get("wish_id") != null) {
            wish_id = Utils.checkIntValIsNull(Integer.parseInt((String) map.get("wish_id")));
        }
        if (map.get("id_techs") != null) {
            id_techs = (String) map.get("id_techs");
        }
        if (map.get("id_projs") != null) {
            id_projects = (String) map.get("id_projs");
        }
        System.out.println("LIST:" + id_techs);
        System.out.println("name:" + empName + ", tech_part_id:" + technology_part_id + ", technologyName:" + technologyName + ", wish_id:" + wish_id);
        if (map.get("id") != null) {
            Integer id = Integer.parseInt(map.get("id").toString());
            employeesProjections = repository.selectEmployees(id, empName, technology_part_id, grade_id, employee_type_id);
            if (employeesProjections.size() > 0) {
                return new ResponseDto<>(true, "OK", employeesProjections);
            }
        } else {
            if (id_techs == null && id_projects == null) {
                employeesProjections = repository.selectEmployees(empName, technology_part_id, technologyName, wish_id, grade_id, employee_type_id);
                if (employeesProjections.size() > 0) {
                    return new ResponseDto<>(true, "OK", employeesProjections);
                }
            } else if (id_techs != null && id_projects == null) {
//                texnologiyalar buyicha multiSelect li filtr
                String[] stringArray = id_techs.split(",");

                // Step 2: Convert each string in the array to an integer and add to a list
                List<Integer> intList = new ArrayList<>();
                for (String s : stringArray) {
                    intList.add(Integer.parseInt(s));
                }

                employeesProjections = repository.selectEmployees(empName, technology_part_id, technologyName, wish_id, intList, grade_id, employee_type_id);
                if (employeesProjections.size() > 0) {
                    return new ResponseDto<>(true, "OK", employeesProjections);
                }
            } else if (id_techs == null && id_projects != null) {
//                texnologiyalar buyicha multiSelect li filtr
                String[] stringArray = id_projects.split(",");

                // Step 2: Convert each string in the array to an integer and add to a list
                List<Integer> projs = new ArrayList<>();
                for (String s : stringArray) {
                    projs.add(Integer.parseInt(s));
                }

                employeesProjections = repository.selectEmployeesMs2(empName, technology_part_id, technologyName, wish_id, projs, grade_id, employee_type_id);
                if (employeesProjections.size() > 0) {
                    return new ResponseDto<>(true, "OK", employeesProjections);
                }
            } else {
                String[] stringArray = id_projects.split(",");

                // Step 2: Convert each string in the array to an integer and add to a list
                List<Integer> projs = new ArrayList<>();
                for (String s : stringArray) {
                    projs.add(Integer.parseInt(s));
                }
                String[] stringArray2 = id_techs.split(",");

                // Step 2: Convert each string in the array to an integer and add to a list
                List<Integer> intList = new ArrayList<>();
                for (String s : stringArray) {
                    intList.add(Integer.parseInt(s));
                }

                employeesProjections = repository.selectEmployeesMs3(empName, technology_part_id, technologyName, wish_id, projs, intList, grade_id, employee_type_id);
                if (employeesProjections.size() > 0) {
                    return new ResponseDto<>(true, "OK", employeesProjections);
                }
            }
        }

        return new ResponseDto<>(false, "no data", new ArrayList<>());
    }

    @Override
    public ResponseDto<?> createRow(EmployeeRqDto req) {
        Employee emp = new Employee();
        emp.setName(req.getName());
        Optional<Wish> wishOptional = wishRepository.findById(req.getWish_id());
        if (!wishOptional.isPresent()) {
            return new ResponseDto<>(false, "Wish not found id:" + req.getWish_id());
        }
        emp.setWish(wishOptional.get());
        emp.setCreated_user(Utils.getUser());
        Optional<Department> department = departmentsRepository.findById(req.getDepartments_id());
        if (!department.isPresent()) {
            return new ResponseDto<>(false, "Data not found id:" + req.getDepartments_id());

        }
        emp.setDepartment(department.get());

        try {
            Employee saved = repository.save(emp);
            if (req.getIdsList().size() > 0) {
                List<EmployeeTechnology> et = new ArrayList<>();

                for (Integer techId : req.getIdsList()) {
                    Optional<Technology> technology = technologyRepository.findById(techId);
                    if (!technology.isPresent()) {
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
        if (byId.isPresent()) {
            Employee entity = new Employee();
            entity.setName(req.getName());
            entity.setCreated_user(byId.get().getCreated_user());
            entity.setSame_user(byId.get().getSame_user());
            Optional<Wish> wishOptional = wishRepository.findById(req.getWish_id());
            if (!wishOptional.isPresent()) {
                return new ResponseDto<>(false, "Wish not found id:" + req.getWish_id());
            }
            entity.setWish(wishOptional.get());
            Optional<Department> department = departmentsRepository.findById(req.getDepartments_id());
            if (!department.isPresent()) {
                return new ResponseDto<>(false, "data  not found id:" + req.getDepartments_id());
            }
            entity.setDepartment(department.get());
            entity.setId(id);
            try {
                repository.save(entity);
                Optional<Users> user = usersRepository.findById(byId.get().getSame_user().getId());
                user.get().setFio(req.getName());
                usersRepository.save(user.get());
                Integer empId = byId.get().getId();

                employeeProjectRepository.deleteByEmpId(empId);
                if (req.getIdProjects().size() > 0) {
                    List<EmployeeProject> ep = new LinkedList<>();
                    for (Integer projectId : req.getIdProjects()) {
                        Optional<Project> project = projectRepository.findById(projectId);
                        if (!project.isPresent()) {
                            return new ResponseDto<>(false, "Data not found id:" + projectId);
                        }
                        EmployeeProject ept = new EmployeeProject();
                        ept.setProject(project.get());
                        ept.setEmployee(byId.get());
                        ep.add(ept);
                    }
                    employeeProjectRepository.saveAll(ep);
                }
                if (req.getIdsList().size() > 0) {
                    Optional<TechnologyGrade> notSpecified = gradeRepository.findById(4);
                    employeeTechnologyRepository.deleteByEmp_id(empId);
                    List<EmployeeTechnology> et = new LinkedList<>();
                    for (Integer techId : req.getIdsList()) {
                        EmployeeTechnology empt = new EmployeeTechnology();
                        Optional<Technology> technology = technologyRepository.findById(techId);
                        if (!technology.isPresent()) {
                            return new ResponseDto<>(false, "Data not found id:" + techId);
                        }
                        empt.setEmployee(byId.get());
                        empt.setTechnology(technology.get());
                        empt.setGrade(notSpecified.get());
                        et.add(empt);
                    }
                    employeeTechnologyRepository.saveAll(et);
                }
                return new ResponseDto<>(true, "Muvaffaqiyatli tahrirlandi id:" + id);
            } catch (Exception e) {
                log.error("Tahrirlashda xatolik " + e.getMessage());
                return new ResponseDto<>(false, "Tahrirlashda hatolik id:" + id);
            }
        }
        return new ResponseDto<>(false, "Obyekt topilmadi id:" + id);
    }

    @Override
    public ResponseDto<?> markTechGrades(TechGradeRqDto req) {
        Optional<Employee> byId = repository.findById(req.getEmployee_id());
        if (byId.isPresent()) {
            if (req.getList().size() > 0) {
                for (TechGrade item : req.getList()) {
                    employeeTechnologyRepository.markTechGrades(item.getGrade_id(), req.getEmployee_id(), item.getTech_id());
                }
            } else {
                return new ResponseDto<>(false, "Gradesc not selected!");
            }
        } else {
            return new ResponseDto<>(false, "Obyekt topilmadi id:" + req.getEmployee_id());
        }
        return new ResponseDto<>(true, "Success");
    }
}






