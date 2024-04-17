package uz.firdavs.taskmanager.payload.rs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectRsDto {
    private Integer id;
    private String name;
    private Integer created_user_id;
    private String created_user_name;

}
