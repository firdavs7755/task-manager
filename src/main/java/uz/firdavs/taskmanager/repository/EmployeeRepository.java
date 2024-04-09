package uz.firdavs.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import uz.firdavs.taskmanager.entity.Employee;
import uz.firdavs.taskmanager.projections.EmployeesProjection;
import uz.firdavs.taskmanager.repository.base.BaseRepository;


import java.util.List;

public interface EmployeeRepository extends BaseRepository<Employee,Integer> {

    @Override
    @EntityGraph(attributePaths = {"department"})
    Page<Employee> findAll(Specification<Employee> spec, Pageable pageable);

    @Query(nativeQuery = true,
            value = "select t.technology_part_id,tp.name as technology_part_name, " +
                    "\t\ta.id \t\tas id,\n" +
                    "\t\ta.\"name\" \t\t\tas name,\n" +
                    "\t\td.\"name\" \t\t\tas departments_name,\n" +
                    "\t\td.id \t\t\t\tas departments_id,\n" +
                    "\t\tcast(jsonb_agg(t.\"name\") as text)  as skills,\n" +
                    "\t\tcast(jsonb_agg(t.\"id\") as text)  as skillsId\n" +
                    "\tfrom employee a\n" +
                    "\tleft join employee_technology et on a.id = et.employee_id\n" +
                    "\tleft join department d on d.id = a.department_id \n" +
                    "\tleft join technology t on t.id = et.technology_id\n" +
                    "\tleft join technology_part tp on tp.id = t.technology_part_id\n" +
                    "\tgroup by a.id ,a.\"name\" ,d.\"name\" ,d.id,t.technology_part_id,tp.name ")
    List<EmployeesProjection> selectEmployees();



}
