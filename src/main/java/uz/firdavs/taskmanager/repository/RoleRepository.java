package uz.firdavs.taskmanager.repository;


import uz.firdavs.taskmanager.entity.Role;
import uz.firdavs.taskmanager.enums.RoleNames;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import java.util.List;

public interface RoleRepository extends BaseRepository<Role,Integer> {

    List<Role> findAllByRole(RoleNames roleNames);


}
