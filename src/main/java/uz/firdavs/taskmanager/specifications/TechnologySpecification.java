package uz.firdavs.taskmanager.specifications;

import org.springframework.data.jpa.domain.Specification;
import uz.firdavs.taskmanager.entity.Technology;

import javax.persistence.criteria.Predicate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TechnologySpecification {

    public static Specification<Technology> filterTable(Map<String, Object> map) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();
            if (map.get("id")!=null){
                predicates.add(criteriaBuilder.equal(root.get("id"),map.get("id")));
            }
            if (map.get("technology_part_id")!=null){
                predicates.add(criteriaBuilder.equal(root.get("technologyPart").get("id"),map.get("technology_part_id")));
            }
            if (map.get("name")!=null){
                predicates.add(criteriaBuilder.like((root.get("name")),"%"+map.get("name")+"%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
