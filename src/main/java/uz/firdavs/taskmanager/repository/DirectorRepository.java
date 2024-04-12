package uz.firdavs.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import uz.firdavs.taskmanager.entity.Director;
import uz.firdavs.taskmanager.entity.Technology;
import uz.firdavs.taskmanager.repository.base.BaseRepository;


public interface DirectorRepository extends BaseRepository<Director,Integer> {
    @Override
    @EntityGraph(attributePaths = {"created_user"})
    Page<Director> findAll(Specification<Director> spec, Pageable pageable);
}
