package uz.firdavs.taskmanager.payload.rs.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author            : Firdavsbek Maxsutaliyev
 * Date:       4/16/2024
 * Time:       8:56 PM
 * Project:    task-manager
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseName {
    private Integer id;
    private String name;
}
