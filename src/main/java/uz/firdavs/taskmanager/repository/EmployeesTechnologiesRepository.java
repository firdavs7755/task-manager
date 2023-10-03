package uz.firdavs.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.firdavs.taskmanager.entity.EmployeesTechnologies;


public interface EmployeesTechnologiesRepository extends JpaRepository<EmployeesTechnologies,Integer> {
    @Modifying
    @Query(value="DELETE FROM employees_technologies WHERE emp_id = ?1", nativeQuery=true)

    int deleteByEmp_id(Integer empid);

}
