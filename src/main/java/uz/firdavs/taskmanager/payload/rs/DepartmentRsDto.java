package uz.firdavs.taskmanager.payload.rs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.firdavs.taskmanager.payload.base.BaseId;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentRsDto extends BaseId {
    private String name;
    private Integer director_id;
    private String director_fio;
}
