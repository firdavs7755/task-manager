package uz.firdavs.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Author            : Firdavsbek Maxsutaliyev
 * Date:       29.05.2024
 * Time:       23:16
 * Project:    task-manager
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class EmployeePurpose {
    @Id
    @SequenceGenerator(name = "employee_purpose_gen", sequenceName = "employee_purpose_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_purpose_gen")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purpose_id")
    private Purpose purpose;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technology_id")
    private Technology technology;

    @CreationTimestamp
    private Date created_date;

}
