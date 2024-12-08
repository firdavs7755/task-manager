package uz.firdavs.taskmanager.payload.rs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.firdavs.taskmanager.payload.base.BaseId;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewCourseApplicationRsDto extends BaseId {
    private String name;
    private String description;
    private Long price;
    private String link;
    private Date created_date;
    private Integer created_user_id;
    private String created_user_fio;
}
