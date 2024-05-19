package uz.firdavs.taskmanager.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TechnologyGrade {

    @Id
    @SequenceGenerator(name = "technology_grade_gen", sequenceName = "technology_grade_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "technology_grade_gen")
    private Integer id;

    private String name;

}
