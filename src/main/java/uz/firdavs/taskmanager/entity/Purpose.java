package uz.firdavs.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Purpose {
    @Id
    @SequenceGenerator(name = "purpose_gen", sequenceName = "purpose_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purpose_gen")
    private Integer id;
    private String name;
    private String description;

    @CreationTimestamp
    private Date created_date;

    @UpdateTimestamp
    private Date updated_date;
}
