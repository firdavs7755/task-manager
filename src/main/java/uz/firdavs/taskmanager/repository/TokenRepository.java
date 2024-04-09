package uz.firdavs.taskmanager.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.firdavs.taskmanager.entity.Token;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TokenRepository extends BaseRepository<Token,Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from token t where t.user_id = :userId and t.is_logged_out = false"
    )
    List<Token>findAllTokenByUserID(Integer userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "delete from token t where t.token =:token")
    int deleteByToken (String token);


    Optional<Token> findTokenByToken (String token);



    Optional<Token> findByToken(String token);
}
