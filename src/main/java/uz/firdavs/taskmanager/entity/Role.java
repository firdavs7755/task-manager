package uz.firdavs.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import uz.firdavs.taskmanager.enums.RoleNames;

import javax.persistence.*;

/**
 * created by: Firdavsbek
 * Date:       26.03.2024
 * Time:       20:24
 * Project:    task-manager
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role {
    @Id
    @SequenceGenerator(name = "role_gen", sequenceName = "role_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_gen")
    private Integer id;

    private String role;

}
