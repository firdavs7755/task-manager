package uz.firdavs.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import uz.firdavs.taskmanager.entity.Course;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import java.util.List;


public interface CourseRepository extends BaseRepository<Course,Integer> {
    @Override
    @EntityGraph(attributePaths = {"created_user"})
    Page<Course> findAll(Specification<Course> spec, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"created_user"})
    List<Course> findAll(Specification<Course> specs);

    @Override
    @EntityGraph(attributePaths = {"created_user"})
    List<Course> findAll();

    @Override
    @EntityGraph(attributePaths = {"created_user"})
    Page<Course> findAll(Pageable pageable);
}
