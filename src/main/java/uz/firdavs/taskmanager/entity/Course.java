package uz.firdavs.taskmanager.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Course {

    @Id
    @SequenceGenerator(name = "course_gen", sequenceName = "course_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_gen")
    private Integer id;

    private String name;
    private String description;
    private Long price;
    private String link;
    private String login;
    private String password;
    @CreationTimestamp
    private Date created_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user_id")
    private Users created_user;

}
