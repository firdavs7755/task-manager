package uz.firdavs.taskmanager.payload.rq;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqUser {
    private String username;
    private String password;
    private String fio;
    private String phone;
    private Integer employee_type_id;

    private List<Integer> idRoles;

//    private Integer user_roles_id;
}
