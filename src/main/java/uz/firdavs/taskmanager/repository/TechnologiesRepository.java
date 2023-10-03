package uz.firdavs.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import uz.firdavs.taskmanager.entity.Technologies;
import uz.firdavs.taskmanager.projections.TechnologiesProjection;

import java.util.List;

@Repository
public interface TechnologiesRepository extends JpaRepository<Technologies,Integer> {

    @Query(nativeQuery = true,
            value = "select t.*,tp.\"name\"  as technology_part_name from technologies t left join technology_part tp on tp.id = t.technology_part_id")
    List<TechnologiesProjection> selectTechnologies();

    int countByName(String name);


    @Query(nativeQuery = true,
            value = "select t.* from technologies t where t.technology_part_id= ?1")
    List<Technologies> selectTechnologiesByTechPartId(Integer partId);
}
