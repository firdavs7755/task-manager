package uz.firdavs.taskmanager.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.firdavs.taskmanager.entity.Role;
import uz.firdavs.taskmanager.enums.RoleNames;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface RoleRepository extends BaseRepository<Role,Integer> {

    @Query(
            nativeQuery = true,
            value = "select role_id as id,r.\"role\" as name from user_role a left join \"role\" r on r.id = a.role_id where a.user_id = :userId"
    )
    List<Map<String,Object>> selectRolesByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "delete from user_role t where t.user_id =:userId")
    int deleteByUserId (Integer userId);


}
