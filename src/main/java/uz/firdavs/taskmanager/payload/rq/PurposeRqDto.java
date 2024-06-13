package uz.firdavs.taskmanager.payload.rq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author            : Firdavsbek Maxsutaliyev
 * Date:       29.05.2024
 * Time:       22:03
 * Project:    task-manager
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurposeRqDto {
    private Integer employee_id;
    private String name;
    private String description;
    private List<Integer> idsList;
}
