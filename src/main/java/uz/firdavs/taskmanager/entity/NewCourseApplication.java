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
public class NewCourseApplication {

    @Id
    @SequenceGenerator(name = "new_course_application_gen", sequenceName = "new_course_application_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "new_course_application_gen")
    private Integer id;

    private String name;
    private String description;
    private Long price;
    private String link;
    @CreationTimestamp
    private Date created_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user_id")
    private Users created_user;

}
