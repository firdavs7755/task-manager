package uz.firdavs.taskmanager.specifications;

import org.springframework.data.jpa.domain.Specification;
import uz.firdavs.taskmanager.entity.Director;

import javax.persistence.criteria.Predicate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DirectorSpecification {

    public static Specification<Director> filterTable(Map<String, Object> map) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();
            if (map.get("id")!=null){
                predicates.add(criteriaBuilder.equal(root.get("id"),map.get("id")));
            }
            if (map.get("fio")!=null){
                predicates.add(criteriaBuilder.like(root.get("fio"),"%"+map.get("fio")+"%"));
            }
            if (map.get("phone")!=null){
                predicates.add(criteriaBuilder.like(root.get("phone"),"%"+map.get("phone")+"%"));
            }
            if (map.get("room")!=null){
                predicates.add(criteriaBuilder.like(root.get("room"),"%"+map.get("room")+"%"));
            }
            if (map.get("email")!=null){
                predicates.add(criteriaBuilder.like(root.get("email"),"%"+map.get("email")+"%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
