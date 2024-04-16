package uz.firdavs.taskmanager.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Top {

    @Id
    @SequenceGenerator(name = "top_gen", sequenceName = "top_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "top_gen")
    private Integer id;

    private String name;
    private String description;
    @CreationTimestamp
    private Date created_date;
    @UpdateTimestamp
    private Date updated_date;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user_id")
    private Users created_user;

}
