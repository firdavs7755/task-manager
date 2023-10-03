package uz.firdavs.taskmanager.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Technologies {

    @Id
    @SequenceGenerator(name = "technologies_gen", sequenceName = "technologies_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "technologies_gen")
    private Integer id;

    private String name;

    @Column(nullable = false)
    private Integer technology_part_id;

}
