package uz.firdavs.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.firdavs.taskmanager.entity.Employee;
import uz.firdavs.taskmanager.entity.EmployeePurpose;
import uz.firdavs.taskmanager.entity.Purpose;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import javax.transaction.Transactional;


public interface EmployeePurposeRepository extends BaseRepository<EmployeePurpose,Integer> {

    @Override
    @EntityGraph(attributePaths = {"employee","technology","purpose"})
    Page<EmployeePurpose> findAll(Specification<EmployeePurpose> spec, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM employee_purpose WHERE employee_id = ?1", nativeQuery=true)
    int deleteByEmpId(Integer empId);

    int countEmployeePurposeByPurpose(Purpose purpose);
}
