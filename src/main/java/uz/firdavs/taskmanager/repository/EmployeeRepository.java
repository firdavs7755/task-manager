package uz.firdavs.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.firdavs.taskmanager.entity.Employee;
import uz.firdavs.taskmanager.projections.EmployeesProjection;
import uz.firdavs.taskmanager.repository.base.BaseRepository;


import java.util.List;

public interface EmployeeRepository extends BaseRepository<Employee,Integer> {

    @Override
    @EntityGraph(attributePaths = {"department","created_user"})
    Page<Employee> findAll(Specification<Employee> spec, Pageable pageable);

    @Query(nativeQuery = true,
            value = " select a.wish_id,w.\"name\" as wish_name,a.created_user_id, u.fio as created_user_name, t.technology_part_id, tp.name as technology_part_name, a.id as id, a.name as name, d.name as departments_name,  d.id as departments_id, cast(jsonb_agg(t.name) as text)  as skills ,  cast(jsonb_agg(t.id) as text)  as skillsId  from employee a left join users u on u.id = a.created_user_id  left join employee_technology et on a.id = et.employee_id left join department d on d.id = a.department_id  left join technology t on t.id = et.technology_id  left join technology_part tp on tp.id = t.technology_part_id  left join wish w on w.id = a.wish_id group by w.\"name\",u.fio,a.id ,a.name,d.name ,d.id,t.technology_part_id,tp.name order by a.id desc")
    List<EmployeesProjection> selectEmployees();


    @Query(nativeQuery = true,
            value = "select t.technology_part_id,tp.name as technology_part_name, a.id as id, a.name as name,d.name as departments_name, d.id as departments_id,cast(jsonb_agg(t.name) as text)  as skills,cast(jsonb_agg(t.id) as text)  as skillsId from employee a left join employee_technology et on a.id = et.employee_id left join department d on d.id = a.department_id  left join technology t on t.id = et.technology_id left join technology_part tp on tp.id = t.technology_part_id  where a.id= :empId group by a.id ,a.name,d.name ,d.id,t.technology_part_id,tp.name")
    List<EmployeesProjection> selectEmployees(Integer empId);



}



