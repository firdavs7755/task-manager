package uz.firdavs.taskmanager.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Departments {

    @Id
    @SequenceGenerator(name = "departments_gen", sequenceName = "departments_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departments_gen")
    private Integer id;

    private String name;

}
