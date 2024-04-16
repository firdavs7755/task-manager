package uz.firdavs.taskmanager.payload.rs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.firdavs.taskmanager.payload.base.BaseId;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeRsDto extends BaseId {
    private String name;
    private Integer department_id;
    private String department_name;
    private Integer created_user_id;
    private String created_user_fio;
    private Integer wish_id;
    private String wish_name;

}
