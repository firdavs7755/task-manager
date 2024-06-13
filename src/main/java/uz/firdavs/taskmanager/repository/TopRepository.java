package uz.firdavs.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import uz.firdavs.taskmanager.entity.Token;
import uz.firdavs.taskmanager.entity.Top;
import uz.firdavs.taskmanager.projections.TopLikeProjection;
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

    @Query(
            nativeQuery = true,
            value = "         \tselect \n" +
                    "\t         \tt.id ,\n" +
                    "\t         \tt.\"name\" as top_name,\n" +
                    "\t         \tu.fio as created_user_name,\n" +
                    "\t         t.created_user_id,\n" +
                    "\t         \tt.created_date ,\n" +
                    "  cast (jsonb_agg(tl.employee_id) as text) as liked_employee, " +
                    "  'Yoqtiirganlar: '||cast (jsonb_agg(e.\"name\") as text) as liked_employee_name, " +
                    "\t         \tcount(tl.id) as likes_cnt         \t\n" +
                    "       \t\tfrom top t\n" +
                    "       \t\tleft join top_like tl on tl.top_id = t.id\n" +
                    "           left join employee e on e.id = tl.employee_id " +
                    "       \t\tleft join users u on u.id = t.created_user_id \n" +
                    "       \t\tgroup by t.id ,t.\"name\"  ,u.fio,t.created_date "
    )
    List<TopLikeProjection> selectTopLike();


}
