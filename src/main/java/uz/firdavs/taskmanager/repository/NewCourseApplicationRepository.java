package uz.firdavs.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import uz.firdavs.taskmanager.entity.NewCourseApplication;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import java.util.List;


public interface NewCourseApplicationRepository extends BaseRepository<NewCourseApplication,Integer> {
    @Override
    @EntityGraph(attributePaths = {"created_user"})
    Page<NewCourseApplication> findAll(Specification<NewCourseApplication> spec, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"created_user"})
    List<NewCourseApplication> findAll(Specification<NewCourseApplication> specs);

    @Override
    @EntityGraph(attributePaths = {"created_user"})
    List<NewCourseApplication> findAll();

    @Override
    @EntityGraph(attributePaths = {"created_user"})
    Page<NewCourseApplication> findAll(Pageable pageable);
}
