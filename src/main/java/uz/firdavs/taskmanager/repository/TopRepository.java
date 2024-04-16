package uz.firdavs.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import uz.firdavs.taskmanager.entity.Top;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import java.util.List;


public interface TopRepository extends BaseRepository<Top,Integer> {
    @Override
    @EntityGraph(attributePaths = {"created_user"})
    Page<Top> findAll(Specification<Top> spec, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"created_user"})
    List<Top> findAll(Specification<Top> spec);
    @Override
    @EntityGraph(attributePaths = {"created_user"})
    List<Top> findAll();
}
