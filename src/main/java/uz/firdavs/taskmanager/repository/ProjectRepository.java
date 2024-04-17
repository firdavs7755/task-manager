package uz.firdavs.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import uz.firdavs.taskmanager.entity.Director;
import uz.firdavs.taskmanager.entity.Project;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import java.util.List;


public interface ProjectRepository extends BaseRepository<Project,Integer> {
    @Override
    @EntityGraph(attributePaths = {"created_user"})
    Page<Project> findAll(Specification<Project> spec, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"created_user"})
    List<Project> findAll(Specification<Project> specs);

    @Override
    @EntityGraph(attributePaths = {"created_user"})
    List<Project> findAll();

    @Override
    @EntityGraph(attributePaths = {"created_user"})
    Page<Project> findAll(Pageable pageable);
}
