package uz.firdavs.taskmanager.specifications;

import org.springframework.data.jpa.domain.Specification;
import uz.firdavs.taskmanager.entity.Director;

import javax.persistence.criteria.Predicate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TechnologyGradeSpecification {

    public static Specification<Director> filterTable(Map<String, Object> map) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();
            if (map.get("id")!=null){
                predicates.add(criteriaBuilder.equal(root.get("id"),map.get("id")));
            }
            if (map.get("name")!=null){
                predicates.add(criteriaBuilder.like(root.get("name"),"%"+map.get("name")+"%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
