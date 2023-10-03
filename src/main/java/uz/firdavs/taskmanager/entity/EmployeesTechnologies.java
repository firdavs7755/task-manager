package uz.firdavs.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class EmployeesTechnologies {
    @Id
    @SequenceGenerator(name = "employees_technologies_gen", sequenceName = "employees_technologies_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_technologies_gen")
    private Integer id;
    private Integer emp_id;
    private Integer tech_id;
}
