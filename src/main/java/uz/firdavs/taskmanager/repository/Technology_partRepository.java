package uz.firdavs.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.firdavs.taskmanager.entity.Technology_part;
import uz.firdavs.taskmanager.projections.ReportProjection;

import java.util.List;

@Repository
public interface Technology_partRepository extends JpaRepository<Technology_part,Integer> , JpaSpecificationExecutor<Technology_part> {

    @Query(nativeQuery = true, value = "select*from technology_part ")
    List<Technology_part> selectTechnologyPart();

    @Query(nativeQuery = true,
            value = "select v.id,v.technology_part_name as \"name\",count(*) as emps_cnt   from(\n" +
                    "\tselect\n" +
                    "\t\ttp.id ,\t\t\t\t\n" +
                    "\t\ttp.\"name\" \t\t\tas technology_part_name\n" +
                    "\tfrom employees a\n" +
                    "\tleft join employees_technologies et on a.id = et.emp_id\n" +
                    "\tleft join technologies t on t.id = et.tech_id\n" +
                    "\tleft join technology_part tp on tp.id = t.technology_part_id \n" +
                    "\tgroup by a.id ,a.\"name\" ,tp.\"name\",tp.id\n" +
                    "\t) v group by v.id ,v.technology_part_name"
    )
    List<ReportProjection> selectEmpsSectionByTechPart();
}
