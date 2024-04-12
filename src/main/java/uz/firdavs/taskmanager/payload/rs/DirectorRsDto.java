package uz.firdavs.taskmanager.payload.rs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.firdavs.taskmanager.payload.base.BaseId;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DirectorRsDto extends BaseId {
    private String fio;
    private String phone;
    private String room;
    private String email;
    private Integer created_user_id;
    private String created_user_fio;

}
