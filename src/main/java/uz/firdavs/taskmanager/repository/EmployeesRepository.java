package uz.firdavs.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 import uz.firdavs.taskmanager.entity.Employees;
import uz.firdavs.taskmanager.projections.EmployeesProjection;


import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {
    @Query(nativeQuery = true,
            value = "select\n" +
                    "\t\ta.id \t\tas id,\n" +
                    "\t\ta.\"name\" \t\t\tas name,\n" +
                    "\t\td.\"name\" \t\t\tas departments_name,\n" +
                    "\t\td.id \t\t\t\tas departments_id,\n" +
                    "\t\tcast(jsonb_agg(t.\"name\") as text)  as skills,\n" +
                    "\t\tcast(jsonb_agg(t.\"id\") as text)  as skillsId\n" +
                    "\tfrom employees a\n" +
                    "\tleft join employees_technologies et on a.id = et.emp_id\n" +
                    "\tleft join departments d on d.id = a.departments_id \n" +
                    "\tleft join technologies t on t.id = et.tech_id\n" +
                    "\tgroup by a.id ,a.\"name\" ,d.\"name\" ,d.id ")
    List<EmployeesProjection> selectEmployees();



}
