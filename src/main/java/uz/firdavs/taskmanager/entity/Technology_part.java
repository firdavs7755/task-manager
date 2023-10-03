package uz.firdavs.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Technology_part {

    @Id
    @SequenceGenerator(name = "technology_part_gen", sequenceName = "technology_part_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "technology_part_gen")
    private Integer id;

    @Column(nullable = false)
    private String name;
}
