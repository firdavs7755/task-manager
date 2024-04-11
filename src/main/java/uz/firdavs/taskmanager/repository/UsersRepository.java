package uz.firdavs.taskmanager.repository;

import org.springframework.data.jpa.repository.Query;
import uz.firdavs.taskmanager.entity.Users;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface UsersRepository extends BaseRepository<Users,Integer> {
    Optional<Users> findByUsername(String username);

    @Query(
            nativeQuery = true,
            value = "select a.*, u.fio as user_fio,u.username ,u.phone ,r.\"role\" as role_name from user_role a left join users u on u.id = a.user_id left join \"role\" r on r.id = a.role_id where a.user_id = :userId"
    )
    List<Map<String,Object>> selectUserRoles(Integer userId);

}
