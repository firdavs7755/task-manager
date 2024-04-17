package uz.firdavs.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.firdavs.taskmanager.entity.Employee;
import uz.firdavs.taskmanager.entity.Token;
import uz.firdavs.taskmanager.projections.EmployeesProjection;
import uz.firdavs.taskmanager.repository.base.BaseRepository;


import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends BaseRepository<Employee, Integer> {

    @Override
    @EntityGraph(attributePaths = {"department", "created_user"})
    Page<Employee> findAll(Specification<Employee> spec, Pageable pageable);

    @Query(nativeQuery = true,
            value = " select a.wish_id,w.\"name\" as wish_name,a.created_user_id, u.fio as created_user_name, t.technology_part_id, tp.name as technology_part_name, a.id as id, a.name as name, d.name as departments_name,  d.id as departments_id, cast(jsonb_agg(t.name) as text)  as skills ,  cast(jsonb_agg(t.id) as text)  as skillsId ,ep.projects,ep.projects_id from employee a left join users u on u.id = a.created_user_id  left join employee_technology et on a.id = et.employee_id left join department d on d.id = a.department_id  left join technology t on t.id = et.technology_id  left join technology_part tp on tp.id = t.technology_part_id         left join (\n" +
                    "\t        select \n" +
                    "\t\t\t\te.employee_id as target_id,\n" +
                    "\t\t\t\tcast(json_agg(p.name) as text) as projects,\n" +
                    "\t\t\t\tcast(json_agg(p.id) as text) as projects_id\n" +
                    "\t\t        from employee_project e\n" +
                    "\t\t        left join project p on p.id = e.project_id\n" +
                    "\t\t        group by e.employee_id\n" +
                    "        ) ep on ep.target_id = a.id " +
                    " left join wish w on w.id = a.wish_id" +
                    " where 1=1 " +
                    "and a.name like '%'||:empName||'%'     " +
                    "and COALESCE(t.technology_part_id,10000)||'' like '%'||:technology_part_id||'%'     " +
                    "  group by w.\"name\",u.fio,a.id ,a.name,d.name ,d.id,t.technology_part_id,tp.name ,ep.projects,ep.projects_id " +
                    "having cast(jsonb_agg(lower(t.name)) as text) like '%'||lower(:technologyName)||'%' order by a.id desc")
    List<EmployeesProjection> selectEmployees(@Param("empName") String empName,@Param("technology_part_id") String technology_part_id,@Param("technologyName") String technologyName);


    @Query(nativeQuery = true,
            value = "select a.wish_id,w.\"name\" as wish_name,a.created_user_id, u.fio as created_user_name, t.technology_part_id, tp.name as technology_part_name, a.id as id, a.name as name, d.name as departments_name,  d.id as departments_id, cast(jsonb_agg(t.name) as text)  as skills ,  cast(jsonb_agg(t.id) as text)  as skillsId ,ep.projects, ep.projects_id from employee a left join users u on u.id = a.created_user_id  left join employee_technology et on a.id = et.employee_id left join department d on d.id = a.department_id  left join technology t on t.id = et.technology_id  left join technology_part tp on tp.id = t.technology_part_id         left join (\n" +
                    "\t        select \n" +
                    "\t\t\t\te.employee_id as target_id,\n" +
                    "\t\t\t\tcast(json_agg(p.name) as text) as projects,\n" +
                    "\t\t\t\tcast(json_agg(p.id) as text) as projects_id\n" +
                    "\t\t        from employee_project e\n" +
                    "\t\t        left join project p on p.id = e.project_id\n" +
                    "\t\t        group by e.employee_id\n" +
                    "        ) ep on ep.target_id = a.id" +
                    "  left join wish w on w.id = a.wish_id" +
                    " where a.id= :empId " +
                    "and a.name like '%'||:empName||'%'     " +
                    "and COALESCE(t.technology_part_id,100000)||'' like '%'||:technology_part_id||'%'     " +
                    " group by w.\"name\",u.fio,a.id ,a.name,d.name ,d.id,t.technology_part_id,tp.name ,ep.projects,ep.projects_id order by a.id desc")
    List<EmployeesProjection> selectEmployees(Integer empId, @Param("empName") String empName,@Param("technology_part_id") String technology_part_id);

    @Query(
            nativeQuery = true,
            value = "select * from employee e where e.same_user_id = :userId"
    )
    Optional<Employee> findBySameUserId(Integer userId);

}



