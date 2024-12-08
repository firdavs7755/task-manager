package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Employee;
import uz.firdavs.taskmanager.entity.EmployeePurpose;
import uz.firdavs.taskmanager.entity.Purpose;
import uz.firdavs.taskmanager.entity.Technology;
import uz.firdavs.taskmanager.payload.rq.PurposeRqDto;
import uz.firdavs.taskmanager.projections.PurposeProjection;
import uz.firdavs.taskmanager.repository.EmployeePurposeRepository;
import uz.firdavs.taskmanager.repository.EmployeeRepository;
import uz.firdavs.taskmanager.repository.PurposeRepository;
import uz.firdavs.taskmanager.repository.TechnologyRepository;
import uz.firdavs.taskmanager.service.PurposeService;
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
public class ImplPurpose implements PurposeService {

    private final PurposeRepository repository;
    private final EmployeePurposeRepository employeePurposeRepository;
    private final EmployeeRepository employeeRepository;
    private final TechnologyRepository technologyRepository;


    @Override
    public ResponseDto<?> selectPurpose(Map<String, Object> map) {
        Integer employee_id = null;
        String technology_part_id="";
        String empName="";
        String id_techs = null;

        if (map.get("employee_id") != null) {
            employee_id = Integer.parseInt((String) map.get("employee_id"));
        }
        if (map.get("empName")!=null){
            empName = Utils.checkStringValIsNull((String) map.get("empName"));
        }
        if (map.get("technology_part_id")!=null){
            technology_part_id = Utils.checkIntValIsNull (Integer.parseInt((String) map.get("technology_part_id")));
        }
        if (map.get("id_techs") != null) {
            id_techs = (String) map.get("id_techs");
        }

        if (employee_id!=null){
//            xodimni uzi login qilganda
            List<PurposeProjection> list = repository.selectPurpose(employee_id);
            return new ResponseDto<>(true, "Ok", list);
        } else if (id_techs!=null){
//                texnologiyalar buyicha multiSelect li filtr
            String[] stringArray = id_techs.split(",");

            // Step 2: Convert each string in the array to an integer and add to a list
            List<Integer> intList = new ArrayList<>();
            for (String s : stringArray) {
                intList.add(Integer.parseInt(s));
            }

            List<PurposeProjection> purposeProjections = repository.selectPurpose(empName, technology_part_id, intList);
            if (purposeProjections.size() > 0) {
                return new ResponseDto<>(true, "OK", purposeProjections);
            }
            return new ResponseDto<>(false,"No data");
        } else {
            //barcha employee lar ruyhati qaytadi. Filterlarni ham shu yerga qushaman
            List<PurposeProjection> list = repository.selectPurpose(empName,technology_part_id);
            return new ResponseDto<>(true, "Ok", list);
        }

    }

    @Override
    public ResponseDto<?> createRow(PurposeRqDto req) {

        Optional<Employee> employee = employeeRepository.findById(req.getEmployee_id());
        if (!employee.isPresent()) {
            return new ResponseDto<>(false, "Employee not found id:" + req.getEmployee_id());
        }
        Purpose purpose = new Purpose();
        purpose.setName(req.getName());
        purpose.setDescription(req.getDescription());
        try {
            Purpose saved = repository.save(purpose);
            if (req.getIdsList().size() > 0) {
                List<EmployeePurpose> ep = new ArrayList<>();

                for (Integer techId : req.getIdsList()) {
                    Optional<Technology> technology = technologyRepository.findById(techId);
                    if (!technology.isPresent()) {
                        return new ResponseDto<>(false, "Tech not found id:" + techId);
                    }
                    EmployeePurpose empp = new EmployeePurpose();
                    empp.setPurpose(saved);
                    empp.setTechnology(technology.get());
                    empp.setEmployee(employee.get());
                    ep.add(empp);
                }
                employeePurposeRepository.saveAll(ep);
            }

            return new ResponseDto<>(true, "Muvaffaqiyatli yaratildi");
        } catch (Exception e) {
            log.error("Malamotlar bilan ishlashda xatolik " + e.getMessage());
            return new ResponseDto<>(false, "Yaratishda xatolik");
        }

    }

    @Override
    public ResponseDto<?> editRowById(PurposeRqDto req, Integer id) {
        Optional<Purpose> byId = repository.findById(id);
        Optional<Employee> employee = employeeRepository.findById(req.getEmployee_id());
        if (!employee.isPresent()) {
            return new ResponseDto<>(false, "Employee not found id:" + req.getEmployee_id());
        }
        if (byId.isPresent()) {
            Purpose entity = new Purpose();
            entity.setName(req.getName());
            entity.setDescription(req.getDescription());
            entity.setId(id);
            try {
                repository.save(entity);

                employeePurposeRepository.deleteByEmpId(req.getEmployee_id());

                if (req.getIdsList().size() > 0) {
                    List<EmployeePurpose> et = new LinkedList<>();
                    for (Integer techId : req.getIdsList()) {
                        EmployeePurpose empt = new EmployeePurpose();
                        Optional<Technology> technology = technologyRepository.findById(techId);
                        if (!technology.isPresent()) {
                            return new ResponseDto<>(false, "Data not found id:" + techId);
                        }
                        empt.setEmployee(employee.get());
                        empt.setTechnology(technology.get());
                        empt.setPurpose(byId.get());
                        et.add(empt);
                    }
                    employeePurposeRepository.saveAll(et);
                } else {
                    employeePurposeRepository.deleteByEmpId(req.getEmployee_id());
                    repository.deleteById(id);
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






