package uz.firdavs.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.firdavs.taskmanager.entity.EmployeeTechnology;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import javax.transaction.Transactional;


public interface EmployeeTechnologyRepository extends BaseRepository<EmployeeTechnology,Integer> {

    @Override
    @EntityGraph(attributePaths = {"employee","technology"})
    Page<EmployeeTechnology> findAll(Specification<EmployeeTechnology> spec, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM employee_technology WHERE employee_id = ?1", nativeQuery=true)
    int deleteByEmp_id(Integer empId);

}
