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
                "        d.*,\n" +
                "        COALESCE(t.cnt,0) as emps_cnt, \n" +
                "\t\tCOALESCE(dev.front_cnt,0) as front_cnt,\n" +
                "        COALESCE(dev.back_cnt,0) as back_cnt\n" +
                "        from department d \n" +
                "        left join (\n" +
                "\t        select\n" +
                "\t        e.department_id as target_id,\n" +
                "\t        count(*) as cnt  \n" +
                "\t        from employee e  group by e.department_id\n" +
                "        ) t on t.target_id = d.id\n" +
                "        left join (\n" +
                "        \t\tselect \n" +
                "\t\t\t\t\td.id as target_id,\n" +
                "\t\t\t\t\tsum(etp_f.front_cnt) as front_cnt,\n" +
                "\t\t\t\t\tsum(etp_f.back_cnt) as back_cnt\n" +
                "\t\t\t\t\tfrom department d \n" +
                "\t\t\t\t\tleft join employee e on e.department_id = d.id \n" +
                "\t\t\t\t\tleft join (\n" +
                "\t\t\t\t\t\tselect \n" +
                "\t\t\t\t\t\t\tcase when t.technology_part_id = 2 then 1 else 0 end as front_cnt,\n" +
                "\t\t\t\t\t\t\tcase when t.technology_part_id = 3 then 1 else 0 end as back_cnt,\n" +
                "\t\t\t\t\t\t\tet.employee_id  as target_id\n" +
                "\t\t\t\t\t\tfrom employee_technology et \n" +
                "\t\t\t\t\t\tleft join technology t on t.id = et.technology_id\n" +
                "\t\t\t\t\t\tgroup by et.employee_id,t.technology_part_id\n" +
                "\t\t\t\t\t) etp_f on etp_f.target_id = e.id group by d.id\n" +
                "        ) dev on dev.target_id = d.id"
    )
    List<ReportProjection> selectEmpsSectionByDepar();
}

