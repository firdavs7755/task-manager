package uz.firdavs.taskmanager.projections;


import jdk.internal.instrumentation.TypeMapping;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import java.util.List;

public interface EmployeesProjection {
    Integer getId();
    String getName();
    Integer getDepartments_id();
    String getDepartments_name();

//    @Column(columnDefinition = "jsonb")
//    @Type(type = "jsonbType")
    String getSkills();
    String getSkillsId();
}
