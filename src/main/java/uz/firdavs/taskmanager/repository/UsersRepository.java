package uz.firdavs.taskmanager.repository;

import uz.firdavs.taskmanager.entity.Users;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import java.util.Optional;


public interface UsersRepository extends BaseRepository<Users,Integer> {
    Optional<Users> findByUsername(String username);

}
