package uz.firdavs.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.firdavs.taskmanager.entity.Departments;
import uz.firdavs.taskmanager.projections.ReportProjection;

import java.util.List;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments,Integer> {
    @Query(nativeQuery = true,
        value = "select \n" +
                "\t\td.*,\n" +
                "\t\tCOALESCE(t.cnt,0) as emps_cnt from departments d\n" +
                "\tleft join (\n" +
                "\t\tselect \n" +
                "\t\t\te.departments_id as target_id,\n" +
                "\t\t\tcount(*)\t\t as cnt  \n" +
                "\t\tfrom employees e  group by e.departments_id\n" +
                "\t) t on t.target_id = d.id"
    )
    List<ReportProjection> selectEmpsSectionByDepar();
}
