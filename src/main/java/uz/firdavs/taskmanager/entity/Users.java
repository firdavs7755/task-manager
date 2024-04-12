package uz.firdavs.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

/**
 * created by: Firdavsbek
 * Date:       26.03.2024
 * Time:       20:10
 * Project:    task-manager
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Users {
    @Id
    @SequenceGenerator(name = "users_gen", sequenceName = "users_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_gen")
    private Integer id;


    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fio;

    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private List<Role> roles;

    public Users(@NonNull String username, @NonNull String password, String fio, String phone) {
        this.username = username;
        this.password = password;
        this.fio = fio;
        this.phone = phone;
//        this.roles = roles;
    }

    public Users(Integer id, String username, @NonNull String password, String fio, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fio = fio;
        this.phone = phone;
    }
}
