package uz.firdavs.taskmanager.specifications;

import org.springframework.data.jpa.domain.Specification;
import uz.firdavs.taskmanager.entity.Employee;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EmployeeSpecification {

    public static Specification<Employee> filterTable(Map<String, Object> map) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();
            if (map.get("id")!=null){
                predicates.add(criteriaBuilder.equal(root.get("id"),map.get("id")));
            }
            if (map.get("name")!=null){
                predicates.add(criteriaBuilder.like(root.get("name"),"%"+map.get("name")+"%"));
            }
            if (map.get("department_id")!=null){
                predicates.add(criteriaBuilder.equal(root.get("department_id"),map.get("department_id")));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Employee> selectEmployee() {
        return new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.multiselect(
                        root.get("id").alias("id"),
                        root.get("name").alias("name"),
                        root.join("department").get("name").alias("department_name"),
                        root.join("department").get("id").alias("department_id"),
                        criteriaBuilder.function("cast", String.class,
                                criteriaBuilder.function("jsonb_agg", String.class, root.join("technology").get("name"))).alias("skills"),
                        criteriaBuilder.function("cast", String.class,
                                criteriaBuilder.function("jsonb_agg", String.class, root.join("technology").get("id"))).alias("skillsId")
                ).groupBy(root.get("id"), root.get("name"), root.join("department").get("name"), root.join("department").get("id"));
                return null;
            }
        };
    }

}
