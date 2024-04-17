package uz.firdavs.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.firdavs.taskmanager.entity.EmployeeProject;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import javax.transaction.Transactional;


public interface EmployeeProjectRepository extends BaseRepository<EmployeeProject,Integer> {

    @Override
    @EntityGraph(attributePaths = {"employee","project"})
    Page<EmployeeProject> findAll(Specification<EmployeeProject> spec, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM employee_project WHERE employee_id = ?1", nativeQuery=true)
    int deleteByEmpId(Integer empId);

}
