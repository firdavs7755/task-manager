package uz.firdavs.taskmanager.projections;


import org.hibernate.annotations.Type;

import javax.persistence.Column;
import java.util.List;

public interface EmployeesProjection {
    Integer getId();
    String getName();
    Integer getDepartments_id();
    Integer getTechnology_part_id();
    String getTechnology_part_name();
    String getDepartments_name();

//    @Column(columnDefinition = "jsonb")
//    @Type(type = "jsonbType")
    String getSkills();
    String getSkillsId();
}
