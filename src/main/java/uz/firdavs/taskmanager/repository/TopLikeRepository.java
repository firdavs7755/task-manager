package uz.firdavs.taskmanager.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.firdavs.taskmanager.entity.Employee;
import uz.firdavs.taskmanager.entity.Top;
import uz.firdavs.taskmanager.entity.TopLike;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import javax.transaction.Transactional;
import java.util.Optional;


public interface TopLikeRepository extends BaseRepository<TopLike,Integer> {
    Optional<TopLike> findByEmployeeAndTop(Employee employee, Top top);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM top_like WHERE employee_id = ?1 and top_id = ?2", nativeQuery=true)
    int deleteByEmp_id(Integer empId,Integer top_id);

}
