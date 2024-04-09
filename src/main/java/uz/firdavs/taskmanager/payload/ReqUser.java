package uz.firdavs.taskmanager.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqUser {
    @NonNull
    @Column(nullable = false)
    private String username;
    @NonNull
    @Column(nullable = false)
    private String password;
    private String fio;
    private String phone;
//    private Integer user_roles_id;
}
