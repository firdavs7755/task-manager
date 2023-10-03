package uz.firdavs.taskmanager.specifications;

import org.springframework.data.jpa.domain.Specification;
import uz.firdavs.taskmanager.entity.Technology_part;

public class TechnologyPartSpecefication {

    public static Specification<Technology_part> withName(String name){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%"+name.toLowerCase()+"%");
    }
    public static Specification<Technology_part> withId(Integer id){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal((root.get("id")),id);
    }

}
