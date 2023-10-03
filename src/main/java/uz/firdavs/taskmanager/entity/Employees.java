package uz.firdavs.taskmanager.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Employees {
    @Id
    @SequenceGenerator(name = "employees_gen", sequenceName = "employees_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_gen")
    private Integer id;
    private String name;
    private Integer departments_id;
}
