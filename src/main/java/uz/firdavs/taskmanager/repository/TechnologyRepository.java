package uz.firdavs.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;

import uz.firdavs.taskmanager.entity.Technology;
import uz.firdavs.taskmanager.repository.base.BaseRepository;


public interface TechnologyRepository extends BaseRepository<Technology,Integer> {

    @Override
    @EntityGraph(attributePaths = {"technologyPart","created_user"})
    Page<Technology> findAll(Specification<Technology> spec, Pageable pageable);


    int countByName(String name);


/*    @Query(nativeQuery = true,
            value = "select t.* from technologies t where t.technology_part_id= ?1")
    List<Technology> selectTechnologiesByTechPartId(Integer partId);*/
}
