package uz.firdavs.taskmanager.payload.rq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author            : Firdavsbek Maxsutaliyev
 * Date:       17.05.2024
 * Time:       22:45
 * Project:    task-manager
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TechGrade {
    private Integer tech_id;
    private Integer grade_id;
}
