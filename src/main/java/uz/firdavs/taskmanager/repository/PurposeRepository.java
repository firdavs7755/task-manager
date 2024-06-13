package uz.firdavs.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.firdavs.taskmanager.entity.Purpose;
import uz.firdavs.taskmanager.projections.PurposeProjection;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface PurposeRepository extends BaseRepository<Purpose,Integer> {

    @Override
    @EntityGraph(attributePaths = {"employee","technology"})
    Page<Purpose> findAll(Specification<Purpose> spec, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM purpose WHERE employee_id = ?1", nativeQuery=true)
    int deleteByEmpId(Integer empId);



    @Query(nativeQuery = true,
            value = "  select \n" +
                    "\t\te.id as employee_id,\n" +
                    "\t\te.\"name\" as employee_name,\n" +
                    "\t\tp.\"name\" as purpose_name,\n" +
                    "\t\tp.id as purpose_id,\n" +
                    " cast(jsonb_agg(t.name)  as text)   as technology, " +
                    " cast(jsonb_agg(t.id)  as text)   as technology_ids, " +
                    "\t\tcast(jsonb_agg('Nomi:<strong>'||t.name||'</strong> Yaratilgan vaqti:<strong>'||to_char(ep.created_date,'DD.MM.YYYY HH24:MI')||'</strong>') filter (where t.technology_part_id=3) as text)   as backend,\n" +
                    "\t\tcast(jsonb_agg(t.id) filter (where t.technology_part_id=3) as text)   as backend_ids,\n" +
                    "\t\tcast(jsonb_agg('Nomi:<strong>'||t.name||'</strong>, Yaratilgan vaqti:<strong>'||to_char(ep.created_date,'DD.MM.YYYY HH24:MI')||'</strong>') filter (where t.technology_part_id=2) as text)   as frontend,\n" +
                    "\t\tcast(jsonb_agg(t.id) filter (where t.technology_part_id=2) as text)   as frontend_ids,\n" +
                    "\t\tcast(jsonb_agg('Nomi:<strong>'||t.name||'</strong>, Yaratilgan vaqti:<strong>'||to_char(ep.created_date,'DD.MM.YYYY HH24:MI')||'</strong>') filter (where t.technology_part_id=5) as text)  as tester,\n" +
                    "\t\tcast(jsonb_agg(t.id) filter (where t.technology_part_id=5) as text)  as tester_ids\n " +
                    "\t from employee e \n" +
                    "\tleft join employee_purpose ep on ep.employee_id = e.id\n" +
                    "\tleft join purpose p on p.id = ep.purpose_id \n" +
                    "\tleft join technology t on t.id = ep.technology_id \n" +
                    "\tleft join technology_part tp on tp.id = t.technology_part_id\n" +
                    " where e.id= :empId  " +
                    "\tgroup by\n" +
                    "\t\te.id ,\n" +
                    "\t\te.\"name\" ,\n" +
                    "\t\tp.\"name\" ,\n" +
                    "\t\tp.id   ")
    List<PurposeProjection> selectPurpose(Integer empId);


//    hech qanday filtrsiz listni olish un
    @Query(nativeQuery = true,
            value = "  select \n" +
                    "\t\te.id as employee_id,\n" +
                    "\t\te.\"name\" as employee_name,\n" +
                    "\t\tp.\"name\" as purpose_name,\n" +
                    "\t\tp.id as purpose_id,\n" +
                    " cast(jsonb_agg(t.name)  as text)   as technology, " +
                    " cast(jsonb_agg(t.id)  as text)   as technology_ids, " +
                    "\t\tcast(jsonb_agg('Nomi:<strong>'||t.name||'</strong> Yaratilgan vaqti:<strong>'||to_char(ep.created_date,'DD.MM.YYYY HH24:MI')||'</strong>') filter (where t.technology_part_id=3) as text)   as backend,\n" +
                    "\t\tcast(jsonb_agg(t.id) filter (where t.technology_part_id=3) as text)   as backend_ids,\n" +
                    "\t\tcast(jsonb_agg('Nomi:<strong>'||t.name||'</strong>, Yaratilgan vaqti:<strong>'||to_char(ep.created_date,'DD.MM.YYYY HH24:MI')||'</strong>') filter (where t.technology_part_id=2) as text)   as frontend,\n" +
                    "\t\tcast(jsonb_agg(t.id) filter (where t.technology_part_id=2) as text)   as frontend_ids,\n" +
                    "\t\tcast(jsonb_agg('Nomi:<strong>'||t.name||'</strong>, Yaratilgan vaqti:<strong>'||to_char(ep.created_date,'DD.MM.YYYY HH24:MI')||'</strong>') filter (where t.technology_part_id=5) as text)  as tester,\n" +
                    "\t\tcast(jsonb_agg(t.id) filter (where t.technology_part_id=5) as text)  as tester_ids\n " +
                    "\t from employee e \n" +
                    "\tleft join employee_purpose ep on ep.employee_id = e.id\n" +
                    "\tleft join purpose p on p.id = ep.purpose_id \n" +
                    "\tleft join technology t on t.id = ep.technology_id \n" +
                    "\tleft join technology_part tp on tp.id = t.technology_part_id\n" +
                    "\tgroup by\n" +
                    "\t\te.id ,\n" +
                    "\t\te.\"name\" ,\n" +
                    "\t\tp.\"name\" ,\n" +
                    "\t\tp.id   ")
    List<PurposeProjection> selectPurpose();


//    filterlar bn listni olish
    @Query(nativeQuery = true,
            value = "  select \n" +
                    "\t\te.id as employee_id,\n" +
                    "\t\te.\"name\" as employee_name,\n" +
                    "\t\tp.\"name\" as purpose_name,\n" +
                    "\t\tp.id as purpose_id,\n" +
                    " cast(jsonb_agg(t.name)  as text)   as technology, " +
                    " cast(jsonb_agg(t.id)  as text)   as technology_ids, " +
                    "\t\tcast(jsonb_agg('Nomi:<strong>'||t.name||'</strong> Yaratilgan vaqti:<strong>'||to_char(ep.created_date,'DD.MM.YYYY HH24:MI')||'</strong>') filter (where t.technology_part_id=3) as text)   as backend,\n" +
                    "\t\tcast(jsonb_agg(t.id) filter (where t.technology_part_id=3) as text)   as backend_ids,\n" +
                    "\t\tcast(jsonb_agg('Nomi:<strong>'||t.name||'</strong>, Yaratilgan vaqti:<strong>'||to_char(ep.created_date,'DD.MM.YYYY HH24:MI')||'</strong>') filter (where t.technology_part_id=2) as text)   as frontend,\n" +
                    "\t\tcast(jsonb_agg(t.id) filter (where t.technology_part_id=2) as text)   as frontend_ids,\n" +
                    "\t\tcast(jsonb_agg('Nomi:<strong>'||t.name||'</strong>, Yaratilgan vaqti:<strong>'||to_char(ep.created_date,'DD.MM.YYYY HH24:MI')||'</strong>') filter (where t.technology_part_id=5) as text)  as tester,\n" +
                    "\t\tcast(jsonb_agg(t.id) filter (where t.technology_part_id=5) as text)  as tester_ids\n " +
                    "\t from employee e \n" +
                    "\tleft join employee_purpose ep on ep.employee_id = e.id\n" +
                    "\tleft join purpose p on p.id = ep.purpose_id \n" +
                    "\tleft join technology t on t.id = ep.technology_id \n" +
                    "\tleft join technology_part tp on tp.id = t.technology_part_id\n" +
                    " where 1=1 " +
                    " and e.name like '%'||:empName||'%'     " +
                    "and COALESCE(t.technology_part_id,100000)||'' like '%'||:technology_part_id||'%'     " +
                    "\tgroup by\n" +
                    "\t\te.id ,\n" +
                    "\t\te.\"name\" ,\n" +
                    "\t\tp.\"name\" ,\n" +
                    "\t\tp.id   ")
    List<PurposeProjection> selectPurpose(@Param("empName") String empName,
                                          @Param("technology_part_id") String technology_part_id);



//    filterlar bn listni olish (int List)
    @Query(nativeQuery = true,
            value = "  select \n" +
                    "\t\te.id as employee_id,\n" +
                    "\t\te.\"name\" as employee_name,\n" +
                    "\t\tp.\"name\" as purpose_name,\n" +
                    "\t\tp.id as purpose_id,\n" +
                    " cast(jsonb_agg(t.name)  as text)   as technology, " +
                    " cast(jsonb_agg(t.id)  as text)   as technology_ids, " +
                    "\t\tcast(jsonb_agg('Nomi:<strong>'||t.name||'</strong> Yaratilgan vaqti:<strong>'||to_char(ep.created_date,'DD.MM.YYYY HH24:MI')||'</strong>') filter (where t.technology_part_id=3) as text)   as backend,\n" +
                    "\t\tcast(jsonb_agg(t.id) filter (where t.technology_part_id=3) as text)   as backend_ids,\n" +
                    "\t\tcast(jsonb_agg('Nomi:<strong>'||t.name||'</strong>, Yaratilgan vaqti:<strong>'||to_char(ep.created_date,'DD.MM.YYYY HH24:MI')||'</strong>') filter (where t.technology_part_id=2) as text)   as frontend,\n" +
                    "\t\tcast(jsonb_agg(t.id) filter (where t.technology_part_id=2) as text)   as frontend_ids,\n" +
                    "\t\tcast(jsonb_agg('Nomi:<strong>'||t.name||'</strong>, Yaratilgan vaqti:<strong>'||to_char(ep.created_date,'DD.MM.YYYY HH24:MI')||'</strong>') filter (where t.technology_part_id=5) as text)  as tester,\n" +
                    "\t\tcast(jsonb_agg(t.id) filter (where t.technology_part_id=5) as text)  as tester_ids\n " +
                    "\t from employee e \n" +
                    "\tleft join employee_purpose ep on ep.employee_id = e.id\n" +
                    "\tleft join purpose p on p.id = ep.purpose_id \n" +
                    "\tleft join technology t on t.id = ep.technology_id \n" +
                    "\tleft join technology_part tp on tp.id = t.technology_part_id\n" +
                    " where 1=1 " +
                    " and e.name like '%'||:empName||'%'     " +
                    " and t.id in (:intList)  " +
                    " and COALESCE(t.technology_part_id,100000)||'' like '%'||:technology_part_id||'%'     " +
                    "\tgroup by\n" +
                    "\t\te.id ,\n" +
                    "\t\te.\"name\" ,\n" +
                    "\t\tp.\"name\" ,\n" +
                    "\t\tp.id   ")
    List<PurposeProjection> selectPurpose(@Param("empName") String empName,
                                          @Param("technology_part_id") String technology_part_id,
                                          @Param("intList") List<Integer> intList);



}
