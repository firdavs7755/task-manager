package uz.firdavs.taskmanager.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Wish {

    @Id
    @SequenceGenerator(name = "wish_gen", sequenceName = "wish_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wish_gen")
    private Integer id;

    private String name;
}
