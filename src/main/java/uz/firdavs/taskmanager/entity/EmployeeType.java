package uz.firdavs.taskmanager.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class EmployeeType {

    @Id
    @SequenceGenerator(name = "employee_type_gen", sequenceName = "employee_type_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_type_gen")
    private Integer id;

    private String name;
}
