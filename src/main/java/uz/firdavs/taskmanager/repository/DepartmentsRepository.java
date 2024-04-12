package uz.firdavs.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import uz.firdavs.taskmanager.entity.Department;
import uz.firdavs.taskmanager.projections.ReportProjection;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import java.util.List;

public interface DepartmentsRepository extends BaseRepository<Department,Integer> {
    @Override
    @EntityGraph(attributePaths = {"director","created_user"})
    Page<Department> findAll(Specification<Department> spec, Pageable pageable);

    @Query(nativeQuery = true,
        value = "select \n" +
                "\t\td.*,\n" +
                "\t\tCOALESCE(t.cnt,0) as emps_cnt from department d\n" +
                "\tleft join (\n" +
                "\t\tselect \n" +
                "\t\t\te.department_id as target_id,\n" +
                "\t\t\tcount(*)\t\t as cnt  \n" +
                "\t\tfrom employee e  group by e.department_id\n" +
                "\t) t on t.target_id = d.id"
    )
    List<ReportProjection> selectEmpsSectionByDepar();
}
