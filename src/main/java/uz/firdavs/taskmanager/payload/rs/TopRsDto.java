package uz.firdavs.taskmanager.payload.rs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopRsDto {
    private Integer id;
    private String name;
    private String description;
    private Date created_date;
    private Date updated_date;
    private Integer created_user_id;
    private String created_user_name;

}
