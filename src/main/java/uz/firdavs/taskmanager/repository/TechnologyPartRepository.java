package uz.firdavs.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import uz.firdavs.taskmanager.entity.Technology;
import uz.firdavs.taskmanager.entity.TechnologyPart;
import uz.firdavs.taskmanager.projections.ReportProjection;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import java.util.List;

public interface TechnologyPartRepository extends BaseRepository<TechnologyPart,Integer> {

    @Query(nativeQuery = true, value = "select*from technology_part ")
    List<TechnologyPart> selectTechnologyPart();

    @Query(nativeQuery = true,
            value = "select v.id,v.technology_part_name as \"name\",count(*) as emps_cnt   from(\n" +
                    "\tselect\n" +
                    "\t\ttp.id ,\t\t\t\t\n" +
                    "\t\ttp.\"name\" \t\t\tas technology_part_name\n" +
                    "\tfrom employee a\n" +
                    "\tleft join employee_technology et on a.id = et.employee_id\n" +
                    "\tleft join technology t on t.id = et.technology_id\n" +
                    "\tleft join technology_part tp on tp.id = t.technology_part_id \n" +
                    "\tgroup by a.id ,a.\"name\" ,tp.\"name\",tp.id\n" +
                    "\t) v group by v.id ,v.technology_part_name  having v.id is not null "
    )
    List<ReportProjection> selectEmpsSectionByTechPart();

    @Override
    @EntityGraph(attributePaths = {"created_user"})
    Page<TechnologyPart> findAll(Specification<TechnologyPart> spec, Pageable pageable);

}
