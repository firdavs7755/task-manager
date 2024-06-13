package uz.firdavs.taskmanager.projections;

import java.util.Date;

public interface TopLikeProjection {

    Integer getId();
    String getTop_name();
    String getCreated_user_name();
    Integer getCreated_user_id();
    Date getCreated_date();
    Integer getLikes_cnt();
    String getLiked_employee();
    String getLiked_employee_name();


}
