package uz.firdavs.taskmanager.payload.rs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.firdavs.taskmanager.payload.base.BaseId;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TechnologyRsDto extends BaseId {
    private String name;
    private Integer technology_part_id;
    private String technology_part_name;
    private Integer created_user_id;
    private String created_user_fio;
}
